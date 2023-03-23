#
# jarファイルは VS Code からエクスポートしておく
# command + shift + p でパレットを開き，Java: Export Jar... から実行
#

all: jar icns

jar: ../Jbook.jar
	mv ../Jbook.jar Abook.app/Contents/Java/Jbook.jar

icns: Abook.icns
	mv Abook.icns Abook.app/Contents/Resources/Abook.icns

Abook.icns: Abook.iconset/*.png
	iconutil -c icns Abook.iconset
