class ItemService(private val itemRepository: ItemRepository) {

    fun selectRandomItems(numItems: Int): List<Item> {
        return itemRepository.randomItems(numItems)
    }

    fun saveItem(item: Item) {
        itemRepository.save(item)
    }
}