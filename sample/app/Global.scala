import akka.actor._
import java.util.Calendar
import play.api._
import libs.concurrent.Akka
import akka.util.duration._
import testing.Benchmark

object Global extends GlobalSettings{

  case class LogMessage()

  class DateActor extends Actor {
      def receive = {
        case LogMessage if check => Logger.info(new java.util.Date().toString)
      }

  }

  def check = {
    var cal = Calendar.getInstance()
    cal.get(Calendar.MINUTE) % 2 == 0
  }

  var actorJob: Cancellable = _

  override def onStart(app: Application) {
    Logger.info("アプリケーションが実行されたよ")
    val actorSystem = Akka.system(app)
    val dateActor = actorSystem.actorOf(Props[DateActor], name = "dateactor")
    actorJob = actorSystem.scheduler.schedule(0 seconds, 1 minutes, dateActor, LogMessage)
  }

  override def onStop(app: Application) {
    actorJob.cancel()
    Logger.info("Application shutdown...")
  }
}