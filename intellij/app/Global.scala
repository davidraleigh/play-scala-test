import filters.HeadersFilter
import play.api.GlobalSettings
import play.api.mvc.EssentialAction

/**
  * Created by davidraleigh on 11/11/15.
  */
object Global extends GlobalSettings {
  override def doFilter(action: EssentialAction) : EssentialAction = {
    HeadersFilter.noCache(action)
  }
}
