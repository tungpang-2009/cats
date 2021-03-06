package cats
package std

trait SetInstances extends algebra.std.SetInstances {
  implicit val setInstance: Foldable[Set] with MonoidK[Set] =
    new Foldable[Set] with MonoidK[Set] {

      def empty[A]: Set[A] = Set.empty[A]

      def combine[A](x: Set[A], y: Set[A]): Set[A] = x | y

      def foldLeft[A, B](fa: Set[A], b: B)(f: (B, A) => B): B =
        fa.foldLeft(b)(f)

      def partialFold[A, B](fa: Set[A])(f: A => Fold[B]): Fold[B] =
        Fold.partialIterate(fa)(f)

      override def exists[A](fa: Set[A])(p: A => Boolean): Boolean =
        fa.exists(p)

      override def forall[A](fa: Set[A])(p: A => Boolean): Boolean =
        fa.forall(p)

      override def empty[A](fa: Set[A]): Boolean = fa.isEmpty
    }
}
