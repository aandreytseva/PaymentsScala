package model
import model.Statuses.Status

class User(val userId: Int, val name: String, val status: Status) {


  override def toString = s"User($userId, $name, $status)"

  private def canEqual(other: Any): Boolean = other.isInstanceOf[User]

  override def equals(other: Any): Boolean = other match {
    case that: User =>
      that.canEqual(this) &&
        userId == that.userId &&
        name == that.name &&
        status == that.status
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(userId, name, status)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
