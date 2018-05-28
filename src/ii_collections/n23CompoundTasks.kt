package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    // Return the set of customers who ordered the specified product
    return this.customers.filter {
       e -> e.orders.flatMap {
            f->f.products
        }.contains(product)
    }.toSet()
}

fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    // Return the most expensive product among all delivered products
    // (use the Order.isDelivered flag)
    return this.orders.flatMap { e-> e.products.filter { e.isDelivered } }.maxBy { f->f.price }
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    // Return the number of times the given product was ordered.
    // Note: a customer may order the same product for several times.
   return this.customers.fold(0, {
       partResult, e-> e.orders.flatMap { f->f.products }.count { g->g ==product } + partResult
   })
}
