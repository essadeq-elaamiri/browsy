package browsy.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import browsy.entities.Bookmark;
import browsy.entities.Folder;
import browsy.entities.Page;

public class BookmarkDA extends DataAccessAbs<Bookmark>{

	private final String TABLE_NAME = "bookmark";
	private final String [] COLS = {"id", "pageId", "folderId", "createdAt"};




	@Override
	public List<Bookmark> getAll() {
		String sql = this.GET_ALL.replace("{{TABLE_NAME}}", TABLE_NAME);
		List<Bookmark> bookmarks = new ArrayList<>();
		Statement statement = null; 
		ResultSet result = null;
		Bookmark bookmark;
		Folder folder;
		Page page;
		FolderDA folderDa = new FolderDA();
		PageDA pageDa = new PageDA();

		try {
			statement = this.connection.createStatement();
			result = statement.executeQuery(sql);
			while(result.next()) {
				bookmark = new Bookmark(result.getInt(0), result.getDate(3));
				page = pageDa.getOneById(result.getInt(1));
				folder = folderDa.getOneById(result.getInt(2));
				bookmark.setPage(page);
				bookmark.setFolder(folder);

				bookmarks.add(bookmark);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(statement, connection);
		}

		return bookmarks;
	}

	@Override
	public Bookmark getOneById(int id) {
		String sql = this.GET_ONE_BY_COL.replace("{{TABLE_NAME}}", TABLE_NAME).replace("{{COL_NAME}}", COLS[0]);
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Bookmark bookmark = null;
		Folder folder;
		Page page;
		FolderDA folderDa = new FolderDA();
		PageDA pageDa = new PageDA();

		try {
			preparedStatement = DAUtils.initializePreparedStatement(connection, sql, false, id);
			result = preparedStatement.executeQuery();
			if(result.next()) {
				bookmark = new Bookmark(result.getInt(0), result.getDate(3));
				page = pageDa.getOneById(result.getInt(1));
				folder = folderDa.getOneById(result.getInt(2));
				bookmark.setPage(page);
				bookmark.setFolder(folder);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(result);
			DAUtils.closeRessources(preparedStatement);
		}


		return bookmark;
	}

	@Override
	public List<Bookmark> getAllByKeyword(String keyword) { //by name
		String sql = this.GET_ALL_LIKE.replace("{{TABLE_NAME}}", TABLE_NAME).replace("{{COL_NAME}}", COLS[3]);
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Bookmark bookmark = null;
		Folder folder;
		Page page;
		FolderDA folderDa = new FolderDA();
		PageDA pageDa = new PageDA();
		List<Bookmark> bookmarks = new ArrayList<Bookmark>();

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, false, "%"+keyword+"%");
			result = preparedStatement.executeQuery();
			while(result.next()) {
				bookmark = new Bookmark(result.getInt(0), result.getDate(3));
				page = pageDa.getOneById(result.getInt(1));
				folder = folderDa.getOneById(result.getInt(2));
				bookmark.setPage(page);
				bookmark.setFolder(folder);
				bookmarks.add(bookmark);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(result);
			DAUtils.closeRessources(preparedStatement);
		}
		return bookmarks;
	}

	@Override
	public void save(Bookmark bookmark) {
		String insertCols = COLS[1]+","+COLS[2]+","+COLS[3];
		String sql = this.INSERT.
				replace("{{TABLE_NAME}}", TABLE_NAME).
				replace("{{INSERT_COLS}}", insertCols).
				replace("{{VALUES_?}}", "?,?,?");

		PreparedStatement preparedStatement = null;
		boolean status = false;

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, true, bookmark.getPage().getId(), bookmark.getFolder().getId(), bookmark.getCreatedAt());
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
		String updates = COLS[1]+"=?, "+COLS[2]+"=? "+COLS[2]+"=? ";
		String sql = this.UPDATE.
				replace("{{TABLE_NAME}}", TABLE_NAME).
				replace("{{UPDATES}}", updates).
				replace("{{UPDATE_CONDITION_COL}}", COLS[0]);

		PreparedStatement preparedStatement = null;
		boolean status = false;

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, true, newBookmark.getPage().getId(), newBookmark.getFolder().getId(), newBookmark.getCreatedAt() , id);
			status = preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(preparedStatement);
		}
	}

}
