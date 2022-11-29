package clinic;

/**
 * Error listener interface
 * 
 * It is used by {@link Clinic#loadData(String,ErrorListener)} 
 * to notify offending lines by calling the {@link #offending} method.
 *
 */
public interface ErrorListener {
	/**
	 * Accepts the offending line,
	 * i.e. the line that caused a read error
	 * and was discarded
	 * 
	 * @param line
	 */
	void offending(String line);
}
