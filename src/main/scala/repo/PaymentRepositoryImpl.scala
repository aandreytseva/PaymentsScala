package repo

import model.Payment

import java.util.UUID

class PaymentRepositoryImpl extends PaymentRepository {
  private var payments: Map[UUID, Payment] = Map()

  override def findById(paymentId: UUID): Option[Payment] = {
    require(paymentId != null, "Payment must not be null")
    payments.get(paymentId)
  }

  override def findAll(): List[Payment] =
    payments.values.toList

  override def save(payment: Payment): Payment = {
    require(payment != null, "Payment must not be null")
    if (payments.contains(payment.paymentId))
      throw new IllegalArgumentException(s"Payment with id ${payment.paymentId} already saved")
    payments = payments + (payment.paymentId -> payment)
    payment
  }

  override def editMessage(paymentId: UUID, message: String): Payment = {
    val paymentOpt = payments.get(paymentId)
    if (paymentOpt.isEmpty)
      throw new NoSuchElementException(s"Payment with id $paymentId doesn`t exist")

    val existingPayment = paymentOpt.get
    val editedPayment = new Payment(existingPayment.paymentId, existingPayment.userId, existingPayment.amount, message)
    payments += (paymentId -> editedPayment)
    editedPayment
  }

}
