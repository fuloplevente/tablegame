package com.softtech.jdbc;

import java.sql.SQLException;

/**
 * Subclass of DatabaseException with specific knowledge of various Firebird sql error codes.
 * Created on March 7, 2008
 * @author Jeff Smith
 */
public class FirebirdException extends DatabaseException
{
	/**
	 * FirebirdException Constructor
	 * @param msg exception message
	 * @param e SQLException
	 */
	public FirebirdException (String msg, SQLException e)
	{
		super(msg, e);
	}

	/**
	 * FirebirdException Constructor
	 * @param msg exception message
	 */
	public FirebirdException (String msg)
	{
		super(msg);
	}

	/**
	 * Was db exception caused by bad sql grammer (a typo)
	 * return true or false
	 */
	public boolean isBadSQLGrammar()
	{
		return (sqlErrorCode == -104 || sqlErrorCode == -204 || sqlErrorCode == -206 || sqlErrorCode == -518 || sqlErrorCode == -804);
	}

	/**
	 * Was db exception caused by a data integrity violation
	 * @return true or false
	 */
	public boolean isDataIntegrityViolation()
	{
		return(sqlErrorCode == -616 || sqlErrorCode == -617 || sqlErrorCode == -901);
	}

	/**
	 * Was db exception caused by database being unavailable
	 * @return true or false
	 */
	public boolean isDatabaseUnavailable()
	{
		return(sqlErrorCode == -904);
	}

	/**
	 * Was db exception caused by referencing a invalid bind parameter name
	 * @return true or false
	 */
	public boolean isInvalidBindVariableName()
	{
		return(false);
	}

	/**
	 * Was db exception caused by referencing a non existent table or view
	 * @return true or false
	 */
	public boolean isNonExistentTableOrViewOrCol()
	{
		return(sqlErrorCode == -204);
	}


	/**
	 * Was db exception caused by a row lock error or some other sql query timeout
	 * return true or false
	 */
	public boolean isRowlockOrTimedOut()
	{
		return(sqlErrorCode == -615);
	}

	/**
	 * Was db exception caused by a duplicate record (unique constraint) violation
	 */
	public boolean isUniqueConstraintViolation()
	{
		return(sqlErrorCode == -803);
	}

	/** was db exception caused by a an unbound variable (parameter)
	 * @return boolean
	 */
	public boolean isVarParameterUnbound()
	{
		return(sqlErrorCode == -901);
	}

}
