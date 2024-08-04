package model

import java.util.UUID

class Payment (val paymentId: UUID = UUID.randomUUID(), val userId: Int, val amount: Double, val message: String) {

  override def toString = s"Payment($paymentId, $userId, $amount, $message)"


  private def canEqual(other: Any): Boolean = other.isInstanceOf[Payment]

  override def equals(other: Any): Boolean = other match {
    case that: Payment =>
      that.canEqual(this) &&
        paymentId == that.paymentId &&
        userId == that.userId &&
        amount == that.amount &&
        message == that.message
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(paymentId, userId, amount, message)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
