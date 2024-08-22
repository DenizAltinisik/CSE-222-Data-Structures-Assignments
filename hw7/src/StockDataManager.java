class StockDataManager {
    private AVLTree avlTree;

    public StockDataManager() {
        avlTree = new AVLTree();
    }
    public StockDataManager(int initialNode) {
        avlTree = new AVLTree();
    }

    public void addStock(String symbol, double price, long volume, long marketCap) {
        Stock stock = new Stock(symbol, price, volume, marketCap);
        avlTree.insert(stock);
    }

    public void removeStock(String symbol) {
        avlTree.delete(symbol);
    }

    public Stock searchStock(String symbol) {
        return avlTree.search(symbol);
    }

    public void updateStock(String symbol, String newSymbol, double newPrice, long newVolume, long newMarketCap) {
        Stock stock = avlTree.search(symbol);
        if (stock != null) {
            removeStock(symbol);
            stock.setSymbol(newSymbol);
            stock.setPrice(newPrice);
            stock.setVolume(newVolume);
            stock.setMarketCap(newMarketCap);
            addStock(newSymbol, newPrice, newVolume, newMarketCap);
        }
    }
}