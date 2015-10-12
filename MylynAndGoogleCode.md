# Introduction #

Some starting point:
http://www.jroller.com/alexRuiz/entry/using_mylyn_with_google_code

There are some projects that are under development:
http://code.google.com/p/googlecode-mylyn-connector/
http://code.google.com/a/eclipselabs.org/p/projecthosting-connector-for-mylyn/

http://www.eclipse.org/mylyn/downloads/

# Details #
This is for Eclipse Helios

  * Install Mylyn:
    * Update site:
> > http://download.eclipse.org/tools/mylyn/update/e3.4/
    * Projects (I'm not sure if all from them are required):
      * Mylyn Task List
      * Mylyn Task-Focused Interface
      * Mylyn Bridge: Eclipse ID
      * Mylyn Bridge: Java Development
      * Mylyn Bridge: Plug-in Development
      * Mylyn Bridge: Team Support
      * Mylyn Connector: Bugzilla
      * Mylyn WikiText

  * Install Web Templates connector:
    * Update site:
> > Mylyn Incubator - http://download.eclipse.org/tools/mylyn/update/incubator
    * Projects:
      * Mylyn Connector: Web Templates (Advanced)

  * Follow instructions from http://www.jroller.com/alexRuiz/entry/using_mylyn_with_google_code from 2 to 6

  * Server
> > http://code.google.com/p/epf-process-validator/issues
  * Label
> > epf-process-validator (Google Code)
  * Anonymous
> > check
  * ~~Advanced configuration~~
    * ~~Query Pattern~~
      * ~~from http://www.jroller.com/alexRuiz/entry/using_mylyn_with_google_code, step 8~~
> > > > ~~`<td.+?class="vt col_6".+?onclick="if \(!cancelBubble\) _goIssue\(.+?\)".+?<a.+?href="detail\?id=([0-9]+)">(.+?)</a>.+?</td>` old was: `"({Id}[0-9]+?)","({Status}.*?)","({Type}.*?)","({Owner}.*?)","({Description}.*?)","(.*?)"`\n~~
