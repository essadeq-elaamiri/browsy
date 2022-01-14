package browsy.dataAccess;

import java.util.List;

public abstract class DataAccessAbs<T> {

	protected final String GET_ALL = "SELECT * FROM {{TABLE_NAME}};";
	protected final String GET_ALL_LIKE = "SELECT * FROM {{TABLE_NAME}} WHERE {{COL_NAME}} LIKE ?";
	protected final String GET_ONE_BY_COL = "SELECT * FROM {{TABLE_NAME}} WHERE {{COL_NAME}}=?;";

	protected final String INSERT = "INSERT INTO "+"{{INSERT_COLS}}"+" VALUES ({{VALUES_?}});";

	protected final String DELETE = "DELETE FROM {{TABLE_NAME}} WHERE {{COL_NAME}}=? ";
	protected final String DELETE_ALL = "DELETE FROM {{TABLE_NAME}} WHERE 1=1";

	protected final String UPDATE = "UPDATE "+"{{TABLE_NAME}}"+" SET "+"{{UPDATES}}"+" WHERE "+"{{UPDATE_CONDITION}}"+"=?";


	public abstract List<T> getAll();
	public abstract T getOneById();
	public abstract T getAllByKeyword(String keyword);

	public abstract void save(T t);

	public abstract void delete(int id);

	public abstract void update(int id, T newt);





}
