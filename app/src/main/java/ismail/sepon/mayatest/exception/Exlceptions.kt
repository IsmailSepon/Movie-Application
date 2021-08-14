package ismail.sepon.mayatest.exception

import java.io.IOException


/**
 * Created by MD ISMAIL HOSSAIN SEPON on 07-Jun-21.
 * ismailhossainsepon@gmail.com
 */
class ApiException(message: String): IOException(message)
class NoInternetException(message: String): IOException(message)