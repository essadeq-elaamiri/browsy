package browsy.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import browsy.entities.Bookmark;

public class BookmarkDA extends DataAccessAbs<Bookmark>{

	private final String TABLE_NAME = "bookmark";
	private final String [] COLS = {"id", "pageId", "folderId", "createdAt"};



	@Override
	public List<Bookmark> getAll() {
		String sql = this.GET_ALL.replace("{{TABLE_NAME}}", TABLE_NAME);
		List<Bookmark> Bookmarks = new ArrayList<>();
		Statement statement = null; 
		ResultSet result = null;
		Bookmark Bookmark;

		try {
			statement = this.connection.createStatement();
			result = statement.executeQuery(sql);
			while(result.next()) {
				Bookmark = new Bookmark(result.getInt(0), result.getDate(1));
				Bookmarks.add(Bookmark);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(statement, connection);
		}

		return Bookmarks;
	}

	@Override
	public Bookmark getOneById(int id) {
		String sql = this.GET_ONE_BY_COL.replace("{{TABLE_NAME}}", TABLE_NAME).replace("{{COL_NAME}}", COLS[0]);
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Bookmark Bookmark = null;

		try {
			preparedStatement = DAUtils.initializePreparedStatement(connection, sql, false, id);
			result = preparedStatement.executeQuery();
			if(result.next()) {
				Bookmark = new Bookmark(result.getInt(0), result.getString(1), result.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(result);
			DAUtils.closeRessources(preparedStatement);
		}


		return Bookmark;
	}

	@Override
	public List<Bookmark> getAllByKeyword(String keyword) { //by name
		String sql = this.GET_ALL_LIKE.replace("{{TABLE_NAME}}", TABLE_NAME).replace("{{COL_NAME}}", COLS[1]);
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Bookmark Bookmark = null;
		List<Bookmark> Bookmarks = new ArrayList<Bookmark>();

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, false, "%"+keyword+"%");
			result = preparedStatement.executeQuery();
			while(result.next()) {
				Bookmark = new Bookmark(result.getInt(0), result.getString(1), result.getString(2));
				Bookmarks.add(Bookmark);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(result);
			DAUtils.closeRessources(preparedStatement);
		}
		return Bookmarks;
	}

	@Override
	public void save(Bookmark Bookmark) {
		String insertCols = COLS[1]+","+COLS[2];
		String sql = this.INSERT.
				replace("{{TABLE_NAME}}", TABLE_NAME).
				replace("{{INSERT_COLS}}", insertCols).
				replace("{{VALUES_?}}", "?,?");

		PreparedStatement preparedStatement = null;
		boolean status = false;

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, true, Bookmark.getName(), Bookmark.getLink());
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
	public void update(int id, Bookmark newBookmark) {
		String updates = COLS[1]+"=?, "+COLS[2]+"=? ";
		String sql = this.UPDATE.
				replace("{{TABLE_NAME}}", TABLE_NAME).
				replace("{{UPDATES}}", updates).
				replace("{{UPDATE_CONDITION_COL}}", COLS[0]);

		PreparedStatement preparedStatement = null;
		boolean status = false;

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, true, newBookmark.getName(), newBookmark.getLink() , id);
			status = preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(preparedStatement);
		}
	}

}
