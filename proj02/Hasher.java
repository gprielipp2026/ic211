/**
 * Prielipp (265112)
 *
 * Standard interface for a Hashing function/algorithm
 */

public interface Hasher
{
  public String getAlgName();
  public String hash(String password) throws Throwable;
}
