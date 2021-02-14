package example

object ShoppingCart {
  val APPLE_PRICE = 60
  val ORANGE_PRICE = 25
  def main(args: Array[String]): Unit = {
    val apple = Item(Symbol("apple"), APPLE_PRICE)
    val orange = Item(Symbol("orange"), ORANGE_PRICE)

    assert(checkout(Seq(apple, orange, apple, orange)) == BigDecimal(170)/100)
    assert(checkout((0 until 20).map(_ => apple) ++ (0 until 10).map(_ => orange)) == BigDecimal(1450)/100)


  }

  def checkout(items: Seq[Item]): BigDecimal = BigDecimal(items.map(_.priceInPence).sum)/100
}

case class Item(name: Symbol, priceInPence: Int)
