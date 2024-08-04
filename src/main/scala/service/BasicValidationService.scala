package service

import model.User
import model.Statuses.ACTIVE

import java.util.UUID

class BasicValidationService extends ValidationService {
  override def validateAmount(amount: Double): Unit = {
    if (amount == null) {
      throw new IllegalArgumentException("Amount must not be null")
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be greater than 0")
    }
  }

  override def validatePaymentId(paymentId: UUID): Unit =
    if (paymentId == null)
      throw new IllegalArgumentException("Payment must not be null")

  override def validateUserId(userId: Int): Unit =
    if (userId == null)
      throw new IllegalArgumentException("User id must not be null")

  override def validateUser(user: User): Unit =
    if (!user.status.equals(ACTIVE))
      throw new IllegalArgumentException("User with id " + user.userId + " not in ACTIVE statud")

  override def validateMessage(message: String): Unit = {
    if (message == null || message.isEmpty)
      throw new IllegalArgumentException("Message must not be null or empty")
    if (message.length > 255)
      throw new IllegalArgumentException("Message must not exceed 255 characters")
  }
}
