import repo.{PaymentRepositoryImpl, UserRepositoryImpl}
import service.{BasicValidationService, PaymentService, ValidationService}
import model.Statuses.ACTIVE
import model.Statuses.INACTIVE
import java.util.UUID

object Main extends App {

  val userRepository = new UserRepositoryImpl()
  val paymentRepository = new PaymentRepositoryImpl()

  val validationService: ValidationService = new BasicValidationService()
  val paymentService = new PaymentService(userRepository, paymentRepository, validationService)

  var createdPaymentId: Option[UUID] = None
  try {
    val payment = paymentService.createPayment(1, 100.0)
    createdPaymentId = Some(payment.paymentId)
    println(s"Payment created: ${payment.paymentId}, ${payment.amount}, ${payment.message}")
  } catch {
    case ex: Exception => println(s"Error creating payment: ${ex.printStackTrace()}")
  }

  // Edit payment message
  createdPaymentId match {
    case Some(paymentId) =>
      try {
        val editedPayment = paymentService.editPaymentMessage(paymentId, "New message")
        println(s"Edited payment message: ${editedPayment.message}")
      } catch {
        //none
        case ex : Exception =>
          println("Failed to create payment, cannot edit message")
      }
  }

  // Get all payments
  println("All payments : " + paymentRepository.findAll())

  // Get all payments more that x amount
  try {
    val payments = paymentService.getAllByAmountMoreThan(50.0)
    println("Payments more that 50.0: ")
    payments.foreach(p => println(s"${p.paymentId}: ${p.amount}"))
  } catch {
    case ex: Exception => println(s"Error retrieving payments: ${ex.printStackTrace()}")
  }

  // Get all Users
  val user = userRepository.createUser("Anastasia", ACTIVE)
  println("New user = " + user)
}
