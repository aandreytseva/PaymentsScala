package repo

import model.Payment

import java.util.UUID

trait PaymentRepository {
  def findById(paymentId: UUID): Option[Payment]
  def findAll(): List[Payment]
  def save(payment: Payment): Payment
  def editMessage(paymentId: UUID, message: String): Payment

}
