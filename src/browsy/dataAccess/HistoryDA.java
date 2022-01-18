package browsy.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import browsy.entities.History;
import browsy.entities.Page;

public class HistoryDA extends DataAccessAbs<History> {
	private final String TABLE_NAME = "history";
	private final String [] COLS = {"id", "pageId", "createdAt"};

	@Override
	public List<History> getAll() {
		String sql = this.GET_ALL.replace("{{TABLE_NAME}}", TABLE_NAME);
		List<History> histories = new ArrayList<>();
		Statement statement = null; 
		ResultSet result = null;
		History history;
		Page page;
		PageDA pageDa = new PageDA();

		try {
			statement = this.connection.createStatement();
			result = statement.executeQuery(sql);
			while(result.next()) {
				history = new History(result.getInt(COLS[0]), result.getDate(COLS[2]));
				page = pageDa.getOneById(result.getInt(COLS[1]));
				history.setPage(page);

				histories.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(statement, connection);
		}

		return histories;
	}

	@Override
	public History getOneById(int id) {
		String sql = this.GET_ONE_BY_COL.replace("{{TABLE_NAME}}", TABLE_NAME).replace("{{COL_NAME}}", COLS[0]);
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		History history = null;
		Page page;
		PageDA pageDa = new PageDA();

		try {
			preparedStatement = DAUtils.initializePreparedStatement(connection, sql, false, id);
			result = preparedStatement.executeQuery();
			if(result.next()) {
				history = new History(result.getInt(COLS[0]), result.getDate(COLS[2]));
				page = pageDa.getOneById(result.getInt(COLS[1]));
				history.setPage(page);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(result);
			DAUtils.closeRessources(preparedStatement);
		}


		return history;
	}

	@Override
	public List<History> getAllByKeyword(String keyword) { //by name
		String sql = this.GET_ALL_LIKE.replace("{{TABLE_NAME}}", TABLE_NAME).replace("{{COL_NAME}}", COLS[2]);
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		History history = null;
		Page page;
		PageDA pageDa = new PageDA();
		List<History> histories = new ArrayList<History>();

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, false, "%"+keyword+"%");
			result = preparedStatement.executeQuery();
			while(result.next()) {
				history = new History(result.getInt(COLS[0]), result.getDate(COLS[2]));
				page = pageDa.getOneById(result.getInt(COLS[1]));
				history.setPage(page);
				histories.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(result);
			DAUtils.closeRessources(preparedStatement);
		}
		return histories;
	}

	@Override
	public int save(History history) {
		String insertCols = COLS[1]+","+COLS[2];
		String sql = this.INSERT.
				replace("{{TABLE_NAME}}", TABLE_NAME).
				replace("{{INSERT_COLS}}", insertCols).
				replace("{{VALUES_?}}", "?,?");

		PreparedStatement preparedStatement = null;
		boolean status = false;

		try {
			preparedStatement = DAUtils.initializePreparedStatement(this.connection, sql, true, history.getPage().getId(), history.getCreatedAt());
			status = preparedStatement.execute();

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
	public void update(int id, History newBookmark) {
		String updates = COLS[1]+"=?, "+COLS[2]+"=? ";
		String sql = this.UPDATE.
				replace("{{TABLE_NAME}}", TABLE_NAME).
				replace("{{UPDATES}}", updates).
				replace("{{UPDATE_CONDITION_COL}}", COLS[0]);

		PreparedStatement preparedStatement = null;
		boolean status = false;

		try {
			preparedStatement = 
					DAUtils.initializePreparedStatement(this.connection, sql, false, newBookmark.getPage().getId(), newBookmark.getCreatedAt() , id);
			status = preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DAUtils.closeRessources(preparedStatement);
		}
	}
}
