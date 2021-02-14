package example

object ShoppingCart {
  val APPLE_PRICE = 60
  val ORANGE_PRICE = 25
  def main(args: Array[String]): Unit = {
    val apple = Item(Symbol("apple"), APPLE_PRICE)
    val orange = Item(Symbol("orange"), ORANGE_PRICE)

    assert(checkout(Seq(apple, orange, apple, orange)) == APPLE_PRICE * 2 + ORANGE_PRICE * 2)
    assert(checkout((0 until 20).map(_ => apple) ++ (0 until 10).map(_ => orange)) == APPLE_PRICE * 20 + ORANGE_PRICE * 10)
    

  }

  def checkout(items: Seq[Item]): Int = items.map(_.priceInPence).sum
}

case class Item(name: Symbol, priceInPence: Int)
