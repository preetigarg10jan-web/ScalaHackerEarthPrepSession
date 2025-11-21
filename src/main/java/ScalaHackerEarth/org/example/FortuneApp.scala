package ScalaHackerEarth.org.example
import scala.io.Source
import scala.util.Try

object FortuneApp {

  def main(args: Array[String]): Unit = {

    // Path to your CSV file
    val csvPath = "/Users/anujgarg/Desktop/projects/Scala HackerEarth Prep Session/src/main/resources/fortune1000.csv"

    println(s"Reading CSV from: $csvPath")

    // Read file lines
    val lines = Source.fromFile(csvPath).getLines().toList

    // Parse integers safely
    val numbers: Set[Int] = lines.flatMap { line =>

      // Extract digits from each CSV line
      val numOpt = "\\d+".r.findFirstIn(line).flatMap(s => Try(s.toInt).toOption)

      numOpt match {
        case Some(n) => Some(n)
        case None =>
          println(s"Skipping invalid CSV row: [$line]")
          None
      }
    }.toSet

    println()
    println("====== OUTPUT ======")
    println(s"Parsed numbers: $numbers")

    val fortune = lines.flatMap { line =>
      try {
        // Split CSV correctly (handles quoted commas)
        val cols = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)").map(_.trim)

        // Helper: extract the first number from a string like "Fortune(2)" or "  10 "
        def extractInt(s: String): Int =
          "\\d+".r.findFirstIn(s).map(_.toInt).getOrElse(0)

        def extractDouble(s: String): Double =
          "\\d+(\\.\\d+)?".r.findFirstIn(s).map(_.toDouble).getOrElse(0.0)

        // Build the tuple
        Some(
          extractInt(cols(0)), // Rank
          cols(1).replace("\"", ""), // Company
          cols(2).replace("\"", ""), // Sector
          cols(3).replace("\"", ""), // Industry
          cols(4).replace("\"", ""), // Location
          extractDouble(cols(5)), // Revenue
          extractDouble(cols(6)), // Profits
          extractInt(cols(7)) // Employees
        )
      } catch {
        case e: Exception =>
          println(s"Skipping invalid row: $line")
          None
      }
    }.toSet

    println("Question 1:...............")
    val topTen = fortune.toSeq.sortBy{
      case(rank, _, _, _,_,_,_,_) => rank
    }.take(10)


    println(s"Top 10: $topTen")

    println("Question 2:................. ")
    val mapSec : Map[String, Int] = fortune.groupBy{
      case (_,_,sector,_,_,_,_,_) => sector
    }.map{
      case(sector, item) => sector->item.size


    }
    println("Map grouped by sector: " + mapSec)

    println("Question 4:................. ")
    val profit = fortune.maxBy{
      case (_,_,_,_,_,_,profit,_) => profit
    }


    println("The company with highest profit: " + profit)
    println("The company is: " + profit._2)

    println("Question 5:............. ")
    val industries = fortune.map{
      case(_,_,_,industry, _,_,_,_) => industry
    }.toSet


    println("All unique industries: " + industries)

    println("Question 6:........... ")
    val top5 = fortune.toList
      .sortBy { case (_, _, _, _, _, _, _, employees) => -employees } // descending
      .take(5)


    println("Top 5 highest employees list: " + top5)
    println("The companies are: ")


    top5.foreach{
      case (_, company, _, _,_,_,_,_) => println(company)
    }

    println("Question 7: .............")
    val filtered = fortune.filter {
      case (_, _, _, _, location, _, _, _) => location.endsWith("CA")
    }
    println("Companies that are Filtered with location that ends with CA: ")
    filtered.foreach {
      case (_, company, _, _, _, _, _, _) => println(company)
    }


    println("\nQuestion 8:")
    val totalRevenue: Map[String, Double] =
      fortune
        .groupBy { case (_, _, sector, _, _, _, _, _) => sector } // group by sector
        .map { case (sector, items) =>
          sector -> items.map { case (_, _, _, _, _, revenue, _, _) => revenue }.sum
        }
    println("total revenue for each Sector: " + totalRevenue)

    println("\nQuestion 9:")
    val technology = fortune.filter{
      case(_,_,sector,_,_,_,_,_) => sector.equals("Technology")
    }


    val avgTechnology = technology.map{
      case(_,_,_,_,_,_,profit,_) => profit
    }.sum / technology.size


    println("The average profit of all “Technology” sector companies is " + avgTechnology)


    println("Question 10: ")
    val topSector =
      fortune
        .groupBy { case (_, _, sector, _, _, _, _, _) => sector }
        .map { case (sector, items) =>


          val totalRevenue = items.map { case (_, _, _, _, _, revenue, _, _) => revenue }.sum
          sector -> totalRevenue
        }
        .maxBy(_._2) // _._2 = the total revenue
    println("Highest overall revenue for each sector: " + topSector)
    println("\nQuestion 11:")


    val highEmployee = fortune.groupBy{
      case (_, _, sector, _, _, _, _, _) => sector
    }.map{
      case (sector, items) =>
        val topCompany = items.maxBy{ case (_, _, _, _, _, _, _, employees) => employees}
        sector->topCompany
    }


    println("the company with the highest employees for each sector: " + highEmployee)
    println("\nQuestion 12: ")


    val revenueList = fortune.map{
      case (_, company, _, _, _, revenue, _, _) => (company, revenue)
    }.toList.sortBy{
      case (_, revenue) => -revenue
    }


    println("The extracted list is:\n " + revenueList)
    println("\nQuestion 13: ")


    val setOfLocations : Set[String] = fortune.map{
      case (_,_,_,_,location,_,_,_) =>
        val cities = location.split(",")(0).trim
        cities
    }.toSet
    println("List of cities:\n" + setOfLocations)

    println("\nQuestion 14:")
    val stateCounts: Map[String, Int] =
      fortune
        .map { case (_, _, _, _, location, _, _, _) =>
          val parts = location.split(",")
          if (parts.length > 1) parts(1).trim else "Unknown"
        }
        .groupBy(identity)
        .map { case (state, list) => state -> list.size }

    println(stateCounts)


    println("\nQuestion 15: ")
    def profitMargin(profits: Double, revenue: Double) : Double = {
      (profits/revenue) * 100
    }
    val findCompanies = fortune.filter {
      case (_, _, _, _, _, revenue, profits, _) => profitMargin(profits, revenue) > 10
    }.map{
      case (_, company, _, _, _,revenue,profits,_) => company -> profitMargin(profits, revenue)
    }
    println(" Find companies whose profit margin is above 10%: \n" + findCompanies)


    println("\nQuestion 16: ")
    val top3 = fortune.groupBy{
      case (_,_,_,industry, _,_,_,_) => (industry)
    }.map{
      case (industry, item) =>
        val totalProfits = item.map{
          case (_,_,_,_,_,_,profit,_) => profit
        }.sum
        industry -> totalProfits
    }.toList.sortBy{
      case(_, totalProfits) => -totalProfits
    }.take(3)
    println("Top 3 most profitable industries:")
    top3.foreach(println)

    println("\nQuestion 17")
    case class SectorSummary(sector: String, totalRev: Double, totalProfit: Double, avgEmp: Double)
    val summary : List[SectorSummary] = fortune.groupBy{
      case (_,_,sector,_, _,_,_,_) => sector
    }.map{
      case (sector, company) =>
        val totalRev = company.map{case (_,_,_,_,_,revenue,_,_) => revenue}.sum
        val totalProfit = company.map{case (_,_,_,_,_,_,profit,_) => profit}.sum
        val avgEmp = company.map{case (_,_,_,_,_,_,_,employee) => employee}.sum/company.size
        SectorSummary(sector, totalRev, totalProfit, avgEmp)

    }.toList
    println(summary)

        }}
