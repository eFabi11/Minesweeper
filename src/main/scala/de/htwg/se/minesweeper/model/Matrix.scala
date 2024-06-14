package de.htwg.se.minesweeper.model

import com.google.inject.Inject

case class Matrix[T] @Inject() (rows: Vector[Vector[T]]) {
  def this() = this(Vector.empty) // No-argument constructor for Guice
  def this(size: Int, filling: T) = this(Vector.tabulate(size, size) { (row, col) => filling })
  val size: Int = rows.size
  def cell(row: Int, col: Int): T = rows(row)(col)
  def row(row: Int) = rows(row)
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(size, size) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))

  // Add the map method
  def map[B](f: T => B): Matrix[B] = Matrix(rows.map(_.map(f)))
}
