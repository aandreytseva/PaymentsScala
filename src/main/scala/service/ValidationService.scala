package service

import model.User

import java.util.UUID

trait ValidationService {

  def validateAmount(amount: Double)
  def validatePaymentId(paymentId: UUID)
  def validateUserId(userId: Int)
  def validateUser(user: User)
  def validateMessage(message: String)
}
