public class QueueEmptyException extends RuntimeException
{
  QueueEmptyException(String message)
  {
    super(message, null);
  }
}
