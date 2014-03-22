Java Project Template
=====================

Ein beispielhaftes Java Projekt zur Konfiguration der wichtigsten
statischen Codeanalyse Tools. Das Projekt lässt sich sowohl in IntelliJ als auch Eclipse
importieren. Gleichzeitig ist das Projekt als Maven Projekt konfiguriert, so dass sich die
selbe Konfiguration sowohl unter IntelliJ, Eclipse als auch unter Maven 1:1 nutzen lässt.

Folgende wichtigen Punkte sind enthalten:
* Maven Build: Das Projekt lässt sich automatisiert mit folgendem Maven Kommando bauen:
  mvn clean install checkstyle:checkstyle pmd:pmd pmd:cpd findbugs:findbugs cobertura:cobertura
* Eclipse: Das Projekt lässt sich über das m2e Eclipse Plug-in importieren und
  analysiert den Code mit Checkstyle, PMD und FindBugs. Die Code Coverage der Unittests
  lässt sich mit dem EclEmma Plug-in auswerten.
* In den Eclipse Einstellungen (.settings Verzeichnis) wurde eine sinnvolle Konfiguration
  für Compiler Warnungen, Code Formatter und Cleanup Wizards vorgegeben
* Die benötigten Eclipse Plug-ins sind in der Konfiguration eclipse-plugins-p2f abgelegt.
* IntelliJ: Das Projekt lässt sich in IntelliJ importieren und analysiert den Code mit
  Checkstyle, PMD und FindBugs über das QAPlug.
* In den IntelliJ Einstellungen (.idea Verzeichnis) wurde eine sinnvolle Konfiguration
  für Compiler Warnungen, Code Formatter vorgegeben

Damit das eigene Projekt diese Konfigurationen übernimmt, müssen entweder die eigenen
Sourcen in dieses Projekt kopiert werden, oder folgende Dateien bzw. Ordner in das eigene
Projekt kopiert werden:

Eclipse:
* .settings: alle Dateien, definieren die Eclipse Einstellungen
* .checkstyle: definiert die Checkstyle Einstellungen in Eclipse
* .pmd: definiert die PMD Einstellungen in Eclipse
* .fbprefs: definiert die FindBugs Einstellungen in Eclipse

IntelliJ:
* .idea: alle Dateien, definieren die IntelliJ Einstellungen

Beide:
* etc: alle Dateien, hier sind die Regeln der Tools abgelegt. Wird von Eclipse und Maven
genutzt.

