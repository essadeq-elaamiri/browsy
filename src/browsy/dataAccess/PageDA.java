package browsy.dataAccess;

import java.util.List;

import browsy.entities.Page;

public class PageDA extends DataAccessAbs<Page> {

	private final String TABLE_NAME = "page";
	private final String [] COLS = {""};
	private final String GET_ALL_PAGES = this.GET_ALL.replace("{{TABLE_NAME}}", TABLE_NAME);
	private final String GET_ONE_BY_ID = this.GET_ONE_BY_COL.replace("{{TABLE_NAME}}", TABLE_NAME).replace("", newChar)

			@Override
			public List<Page> getAll() {
		//		DAUtils.initializePreparedStatement(connexion, sql, returnGeneratedKeys, objets)
		return null;
	}

	@Override
	public Page getOneById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page getAllByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Page t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int id, Page newt) {
		// TODO Auto-generated method stub

	}

}
