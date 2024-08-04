package repo

import model.User

trait UserRepository {
  def findById(userId: Int): Option[User]

}
