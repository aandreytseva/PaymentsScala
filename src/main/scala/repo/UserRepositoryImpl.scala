package repo

import model.Statuses.{ACTIVE, INACTIVE, Status}
import model.User

import java.util.concurrent.atomic.AtomicInteger

class UserRepositoryImpl extends UserRepository {

  private val userIdCounter = new AtomicInteger(0)
  private var users: Map[Int, User] = Map(
    1 -> new User(1, "John", ACTIVE),
    2 -> new User(2, "Maria", ACTIVE),
    3 -> new User(3, "Peter", INACTIVE),
    4 -> new User(4, "Anna", ACTIVE),
    5 -> new User(5, "David", INACTIVE)
  )

  override def findById(userId: Int): Option[User] = {
    require(userId != null, "User id must not be null")
    users.get(userId)
  }

  def createUser(name: String, status: Status): User = {
    val newUserId = userIdCounter.incrementAndGet()
    val newUser = new User(newUserId, name, status)
    users = users + (newUserId -> newUser) // Adding new user to the Map
    newUser
  }
}
