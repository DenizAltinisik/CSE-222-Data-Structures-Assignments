JC = javac
JFLAGS = -g
TARGET = Main
SOURCES = Main.java TestDataGenerator.java AVLTree.java PerformanceChart.java PerformanceData.java Stock.java StockDataManager.java


default: $(TARGET)

$(TARGET): $(SOURCES)
	$(JC) $(JFLAGS) $^
	java -Xint $(TARGET)

clean:
	rm -f *.class

.PHONY: clean default