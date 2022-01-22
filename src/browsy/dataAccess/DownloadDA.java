package browsy.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import browsy.entities.Download;
import browsy.entities.Folder;
import browsy.entities.Page;

public class DownloadDA extends DataAccessAbs<Download> {


	private final String TABLE_NAME = "download";
	private final String [] COLS = {"id", "name", "downloadedAt", "locationInSystem", "link", "size", "status"};

	@Override
	public List<Download> getAll() {
		String sql = this.GET_ALL.replace("{{TABLE_NAME}}", TABLE_NAME);
		List<Download> downloads = new ArrayList<>();
		Statement statement = null; 
		ResultSet result = null;
		Download download;

		try {
			statement = this.connection.createStatement();
			result = statement.executeQuery(sql);
			while(result.next()) {
				download = new Download(
						result.getInt(COLS[0]),
						result.getString(COLS[1]),
						result.getDate(COLS[2]),
						result.getString(COLS[3]),
						result.getString(COLS[4]),
						result.getDouble(COLS[5]),
						result.getString(COLS[6]));

				downloads.add(download);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(statement);
		}

		return downloads;
	}

	@Override
	public Download getOneById(int id) {
		String sql = this.GET_ONE_BY_COL.replace("{{TABLE_NAME}}", TABLE_NAME).replace("{{COL_NAME}}", COLS[0]);
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Download download = null;

		try {
			preparedStatement = DAUtils.initializePreparedStatement(connection, sql, false, id);
			result = preparedStatement.executeQuery();
			if(result.next()) {
				download = new Download(
						result.getInt(COLS[0]),
						result.getString(COLS[1]),
						result.getDate(COLS[2]),
						result.getString(COLS[3]),
						result.getString(COLS[4]),
						result.getDouble(COLS[5]),
						result.getString(COLS[6]));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(result);
			DAUtils.closeRessources(preparedStatement);
		}


		return download;
	}

	@Override
	public List<Download> getAllByKeyword(String keyword) { //by name
		String sql = this.GET_ALL_LIKE.replace("{{TABLE_NAME}}", TABLE_NAME).replace("{{COL_NAME}}", COLS[1]);
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Download download = null;
		Folder folder;
		Page page;
		FolderDA folderDa = new FolderDA();
		PageDA pageDa = new PageDA();
		List<Download> downloads = new ArrayList<Download>();

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, false, "%"+keyword+"%");
			result = preparedStatement.executeQuery();
			while(result.next()) {
				download = new Download(
						result.getInt(COLS[0]),
						result.getString(COLS[1]),
						result.getDate(COLS[2]),
						result.getString(COLS[3]),
						result.getString(COLS[4]),
						result.getDouble(COLS[5]),
						result.getString(COLS[6]));
				downloads.add(download);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(result);
			DAUtils.closeRessources(preparedStatement);
		}
		return downloads;
	}

	@Override
	public int save(Download download) {
		String insertCols = COLS[1]+","+COLS[2]+","+COLS[3]+","+COLS[4]+","+COLS[5]+","+COLS[6];
		String sql = this.INSERT.
				replace("{{TABLE_NAME}}", TABLE_NAME).
				replace("{{INSERT_COLS}}", insertCols).
				replace("{{VALUES_?}}", "?,?,?,?,?,?");

		PreparedStatement preparedStatement = null;
		boolean status = false;
		int last_inserted_id = 0;
		try {
			preparedStatement = 
					DAUtils.initializePreparedStatement(
							this.connection,
							sql,
							true,
							download.getName(),
							download.getDownloadedAt(),
							download.getLocationInSystem(),
							download.getLink(),
							download.getSize(),
							download.getStatus());
			status = preparedStatement.execute();

			ResultSet rs=preparedStatement.getGeneratedKeys();
			if(rs.next())
			{
				last_inserted_id = rs.getInt(1);
			}
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
	public void update(int id, Download newDownload) {
		String updates = COLS[1]+"=?, "+COLS[2]+"=?, "+COLS[3]+"=?, "+COLS[4]+"=?, "+COLS[5]+"=?, "+COLS[6]+"=? ";
		String sql = this.UPDATE.
				replace("{{TABLE_NAME}}", TABLE_NAME).
				replace("{{UPDATES}}", updates).
				replace("{{UPDATE_CONDITION_COL}}", COLS[0]);

		PreparedStatement preparedStatement = null;
		boolean status = false;

		try {
			preparedStatement =	DAUtils.initializePreparedStatement(
					this.connection,
					sql,
					true,
					newDownload.getName(),
					newDownload.getDownloadedAt(),
					newDownload.getLocationInSystem(),
					newDownload.getLink(),
					newDownload.getSize(),
					newDownload.getStatus(),
					id);
			status = preparedStatement.execute();

			System.out.println(status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(preparedStatement);
		}
	}



}
