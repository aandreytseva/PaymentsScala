package service

import model.Payment
import repo.{PaymentRepository, UserRepository}

import java.util.UUID

class PaymentService(userRepository: UserRepository, paymentRepository: PaymentRepository, validationService: ValidationService) {
  def createPayment(userId: Int, amount: Double): Payment = {
    validationService.validateUserId(userId)
    validationService.validateAmount(amount)

    val foundUser = userRepository.findById(userId)
    val user = foundUser.getOrElse(throw new NoSuchElementException(s"User withd id $userId not found"))
    validationService.validateUser(user)

    val paymentMessage = s"Payment from user ${user.name}"
    val payment = new Payment(UUID.randomUUID(), user.userId, amount, paymentMessage)
    paymentRepository.save(payment)
  }

  def editPaymentMessage(paymentId: UUID, newMessage: String): Payment = {
    validationService.validatePaymentId(paymentId)
    validationService.validateMessage(newMessage)

    paymentRepository.editMessage(paymentId, newMessage)
  }

  def getAllByAmountMoreThan(amount: Double): List[Payment] = {
    paymentRepository.findAll()
      .filter(_.amount > amount)
  }

}
