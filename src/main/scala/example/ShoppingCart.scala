package example

object ShoppingCart {
  val APPLE_PRICE = 60
  val ORANGE_PRICE = 25
  val APPLE = Symbol("apple")
  val ORANGE = Symbol("orange")

  def main(args: Array[String]): Unit = {
    val apple = Item(APPLE, APPLE_PRICE)
    val orange = Item(ORANGE, ORANGE_PRICE)

    assert(checkout(Seq(apple, orange, apple, orange)) == BigDecimal(170) / 100)
    assert(checkout((0 until 20).map(_ => apple) ++ (0 until 10).map(_ => orange)) == BigDecimal(1450) / 100)
    assert(checkout(Seq()) == BigDecimal(0) / 100)

    assert(checkoutOffers(Seq(apple, orange, apple, orange, orange)) == BigDecimal(110) / 100)
    assert(checkoutOffers(Seq()) == BigDecimal(0) / 100)
    assert(checkoutOffers(Seq(apple, orange, apple, apple, orange, orange, orange, orange)) == BigDecimal(220) / 100)
    assert(checkoutOffers(Seq(apple, orange)) == BigDecimal(85) / 100)
    assert(checkoutOffers(Seq(apple, orange, orange)) == BigDecimal(110) / 100)


  }

  def checkout(items: Seq[Item]): BigDecimal = BigDecimal(items.map(_.priceInPence).sum) / 100

  def checkoutOffers(items: Seq[Item]): BigDecimal =
    BigDecimal({
      val apples = items.filter(_.name == APPLE).map(_.priceInPence)
      ((apples.length / 2) + (apples.length % 2)) * apples.headOption.getOrElse(0)
    } + {
      val oranges = items.filter(_.name == ORANGE).map(_.priceInPence)
      (((oranges.length / 3) * 2) + (oranges.length % 3)) * oranges.headOption.getOrElse(0)
    }) / 100
}

case class Item(name: Symbol, priceInPence: Int)
