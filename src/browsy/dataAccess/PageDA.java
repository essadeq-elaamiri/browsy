package browsy.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import browsy.entities.Page;

public class PageDA extends DataAccessAbs<Page> {

	private final String TABLE_NAME = "page";
	private final String [] COLS = {"id", "name", "link"};



	@Override
	public List<Page> getAll() {
		String sql = this.GET_ALL.replace("{{TABLE_NAME}}", TABLE_NAME);
		List<Page> pages = new ArrayList<>();
		Statement statement = null; 
		ResultSet result = null;
		Page page;

		try {
			statement = this.connection.createStatement();
			result = statement.executeQuery(sql);
			while(result.next()) {
				page = new Page(result.getInt(COLS[0]), result.getString(COLS[1]), result.getString(COLS[2]));
				pages.add(page);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(statement, connection);
		}

		return pages;
	}

	@Override
	public Page getOneById(int id) {
		String sql = this.GET_ONE_BY_COL.replace("{{TABLE_NAME}}", TABLE_NAME).replace("{{COL_NAME}}", COLS[0]);
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Page page = null;

		try {
			preparedStatement = DAUtils.initializePreparedStatement(connection, sql, false, id);
			result = preparedStatement.executeQuery();
			if(result.next()) {
				page = new Page(result.getInt(COLS[0]), result.getString(COLS[1]), result.getString(COLS[2]));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(result);
			DAUtils.closeRessources(preparedStatement);
		}


		return page;
	}

	@Override
	public List<Page> getAllByKeyword(String keyword) {
		//by name
		//String sql = this.GET_ALL_LIKE.replace("{{TABLE_NAME}}", TABLE_NAME).replace("{{COL_NAME}}", COLS[1]);
		//by link
		String sql = this.GET_ALL_LIKE.replace("{{TABLE_NAME}}", TABLE_NAME).replace("{{COL_NAME}}", COLS[2]);
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Page page = null;
		List<Page> pages = new ArrayList<>();

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, false, "%"+keyword+"%");
			result = preparedStatement.executeQuery();
			while(result.next()) {
				//change start index from 0 to 1
				//previous WRONG:
				//page = new Page(result.getInt(0), result.getString(1), result.getString(2));
				//new :
				//page = new Page(result.getInt(1), result.getString(2), result.getString(3));
				//new of new
				page = new Page(result.getInt(COLS[0]), result.getString(COLS[1]), result.getString(COLS[2]));

				pages.add(page);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(result);
			DAUtils.closeRessources(preparedStatement);
		}
		return pages;
	}

	@Override
	public int save(Page page) {
		String insertCols = COLS[1]+","+COLS[2];
		String sql = this.INSERT.
				replace("{{TABLE_NAME}}", TABLE_NAME).
				replace("{{INSERT_COLS}}", insertCols).
				replace("{{VALUES_?}}", "?,?");

		PreparedStatement preparedStatement = null;
		boolean status = false;
		int last_inserted_id = 0;

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, true, page.getName(), page.getLink());
			status = preparedStatement.execute();
			//Ajouté par mouad
			//return l'id du page crée
			ResultSet rs=preparedStatement.getGeneratedKeys();
			if(rs.next())
			{
				last_inserted_id = rs.getInt(1);
			}
			//			if(!status) {
			//				//
			//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(preparedStatement);
		}
		return last_inserted_id;

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
	public void update(int id, Page newpage) {
		String updates = COLS[1]+"=?, "+COLS[2]+"=? ";
		String sql = this.UPDATE.
				replace("{{TABLE_NAME}}", TABLE_NAME).
				replace("{{UPDATES}}", updates).
				replace("{{UPDATE_CONDITION_COL}}", COLS[0]);

		PreparedStatement preparedStatement = null;
		boolean status = false;

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, true, newpage.getName(), newpage.getLink() , id);
			status = preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(preparedStatement);
		}
	}

}
