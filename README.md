Beispiel für ein Eclipse Project, das Maven und verschiedenen Statische Analyse Tools nutzt
===========================================================================================

Ein beispielhaftes Eclipse Projekt zur Konfiguration der wichtigsten
statischen Codeanalyse Tools. Das Projekt ist gleichzeitig auch als Maven
Projekt konfiguriert, so dass sich die Konfiguration sowohl unter Eclipse als
auch unter Maven 1:1 nutzen lässt.

Folgende wichtigen Punkte sind enthalten:
* Maven Build: Das Projekt lässt sich automatisiert mit folgendem Maven Kommando bauen:
  mvn clean install checkstyle:checkstyle pmd:pmd pmd:cpd findbugs:findbugs cobertura:cobertura
* Eclipse Build: Das Projekt lässt sich über das m2e Eclipse Plug-in importieren und
  analysiert die Code mit Checkstyle, PMD und FindBugs. Die Code Coverage der Unittests
  lässt sich mit dem EclEmma Plug-in auswerten.
* In den Eclipse Einstellungen (.settings Verzeichnis) wurde eine sinnvolle Konfiguration
  für Compiler Warnungen, Code Formatter und Cleanup Wizards vorgegeben

Damit das eigene Projekt diese Konfigurationen übernimmt, müssen entweder die eigenen
Sourcen in dieses Projekt kopiert werden, oder folgende Dateien bzw. Ordner in das eigene
Projekt kopiert werden:
* .settings: alle Dateien, definieren die Eclipse Einstellungen
* .checkstyle: definiert die Checkstyle Einstellungen in Eclipse
* .pmd: definiert die PMD Einstellungen in Eclipse
* .fbprefs: definiert die FindBugs Einstellungen in Eclipse
* etc: alle Dateien, hier sind die Regeln der Tools abgelegt. Wird von Eclipse und Maven
genutzt.

Das Projekt lässt sich nur in Eclipse 3.8/4.2 (Eclipse for Java Developers) importieren.
Folgenden Eclipse Plug-ins werden mindestens benötigt:
- m2e
- checkstyle
- findbugs
- pmd
- eclemma

Eine sinnvolle Sammlung von Plug-ins ist in der Datei eclipse-plugins.p2f enthalten.
