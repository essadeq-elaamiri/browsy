package browsy.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import browsy.entities.Folder;

public class FolderDA extends DataAccessAbs<Folder> {
	private final String TABLE_NAME = "folder";
	private final String [] COLS = {"id", "name"};



	@Override
	public List<Folder> getAll() {
		String sql = this.GET_ALL.replace("{{TABLE_NAME}}", TABLE_NAME);
		List<Folder> folders = new ArrayList<>();
		//List<Page> pages = new ArrayList<>();
		Statement statement = null; 
		ResultSet result = null;
		Folder folder;

		try {
			statement = this.connection.createStatement();
			result = statement.executeQuery(sql);
			while(result.next()) {
				folder = new Folder(result.getInt(0), result.getString(1));
				folders.add(folder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(statement, connection);
		}

		return folders;
	}

	@Override
	public Folder getOneById(int id) {
		String sql = this.GET_ONE_BY_COL.replace("{{TABLE_NAME}}", TABLE_NAME).replace("{{COL_NAME}}", COLS[0]);
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Folder folder = null;

		try {
			preparedStatement = DAUtils.initializePreparedStatement(connection, sql, false, id);
			result = preparedStatement.executeQuery();
			if(result.next()) {

				//change start index from 0 to 1
				//previous WRONG:
				//folder = new Folder(result.getInt(0), result.getString(1));
				//new :
				folder = new Folder(result.getInt(1), result.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(result);
			DAUtils.closeRessources(preparedStatement);
		}


		return folder;
	}

	@Override
	public List<Folder> getAllByKeyword(String keyword) { //by name
		String sql = this.GET_ALL_LIKE.replace("{{TABLE_NAME}}", TABLE_NAME).replace("{{COL_NAME}}", COLS[1]);
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Folder Folder = null;
		List<Folder> Folders = new ArrayList<Folder>();

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, false, "%"+keyword+"%");
			result = preparedStatement.executeQuery();
			while(result.next()) {
				Folder = new Folder(result.getInt(0), result.getString(1));
				Folders.add(Folder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(result);
			DAUtils.closeRessources(preparedStatement);
		}
		return Folders;
	}

	@Override
	public int save(Folder folder) {
		String insertCols = COLS[1]+","+COLS[2];
		String sql = this.INSERT.
				replace("{{TABLE_NAME}}", TABLE_NAME).
				replace("{{INSERT_COLS}}", insertCols).
				replace("{{VALUES_?}}", "?,?");

		PreparedStatement preparedStatement = null;
		boolean status = false;

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, true, folder.getName());
			status = preparedStatement.execute();
			//			if(!status) {
			//				//
			//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(preparedStatement);
		}

		return 0;
	}

	@Override
	public void delete(int id) {
		String sql = this.DELETE.
				replace("{{TABLE_NAME}}", TABLE_NAME).
				replace("{{COL_NAME}}", COLS[0]);

		PreparedStatement preparedStatement = null;
		boolean status = false;

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, true, id);
			status = preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(preparedStatement);
		}

	}

	@Override
	public void update(int id, Folder newFolder) {
		String updates = COLS[1]+"=? ";
		String sql = this.UPDATE.
				replace("{{TABLE_NAME}}", TABLE_NAME).
				replace("{{UPDATES}}", updates).
				replace("{{UPDATE_CONDITION_COL}}", COLS[0]);

		PreparedStatement preparedStatement = null;
		boolean status = false;

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, true, newFolder.getName() , id);
			status = preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(preparedStatement);
		}
	}
}
