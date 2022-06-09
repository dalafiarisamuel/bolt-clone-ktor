package com.devtamuno.data


object DummyData {

    val DummyUser: User = User(
        id = 1L,
        firstName = "Samuel",
        lastName = "Dalafiari",
        email = "dalafiarisamuel@gmail.com",
        phoneNumber = "+2348141249026",
        isEmailVerified = true
    )

    val DummyTripHistories: List<TripHistory> = listOf(

        TripHistory(
            id = 1,
            address = "Farmville, North Carolina(NC), 27828",
            amount = 1200.0,
            driverName = "Efe Adegoke",
            tripStatus = "CANCELLED",
            createdAt = "18 Apr, 17:03"
        ),

        TripHistory(
            id = 2,
            address = "Shallowater, Texas(TX), 79363",
            amount = 1000.0,
            driverName = "Chinyere Isaac",
            tripStatus = "SUCCESSFUL",
            createdAt = "19 Apr, 17:03"
        ),

        TripHistory(
            id = 3,
            address = "Sarcoxie, Missouri(MO), 64862",
            amount = 2000.0,
            driverName = "Bukola Ismail",
            tripStatus = "SUCCESSFUL",
            createdAt = "20 Apr, 17:03"
        ),

        TripHistory(
            id = 3,
            address = "Oakwood, Georgia(GA), 30566",
            amount = 2000.0,
            driverName = "Kubura Adaobi",
            tripStatus = "SUCCESSFUL",
            createdAt = "1 May, 17:03"
        ),

        TripHistory(
            id = 4,
            address = "Newaygo, Michigan(MI), 49337",
            amount = 2000.0,
            driverName = "Ebiere Mukaram",
            tripStatus = "SUCCESSFUL",
            createdAt = "2 May, 17:03"
        ),

        TripHistory(
            id = 5,
            address = "Borger, Texas(TX), 79007",
            amount = 2000.0,
            driverName = "Fatima Adeoluwa",
            tripStatus = "CANCELLED",
            createdAt = "3 May, 17:03"
        ),

        TripHistory(
            id = 6,
            address = "Flomaton, Alabama(AL), 36441",
            amount = 2000.0,
            driverName = "Omawunmi Kayode",
            tripStatus = "SUCCESSFUL",
            createdAt = "4 May, 17:03"
        ),

        TripHistory(
            id = 7,
            address = "Dyer, Tennessee(TN), 38330",
            amount = 2000.0,
            driverName = "Lola Tella",
            tripStatus = "CANCELLED",
            createdAt = "5 May, 17:03"
        ),

        TripHistory(
            id = 8,
            address = "Rapid City, South Dakota(SD), 57701",
            amount = 2000.0,
            driverName = "Omawunmi Elebiyo",
            tripStatus = "SUCCESSFUL",
            createdAt = "6 May, 17:03"
        ),

        TripHistory(
            id = 9,
            address = "Waco, Georgia(GA), 30182",
            amount = 2000.0,
            driverName = "Adedayo Oluwaseyi",
            tripStatus = "SUCCESSFUL",
            createdAt = "7 May, 17:03"
        ),

        TripHistory(
            id = 10,
            address = "Hudson, Ohio(OH), 44236",
            amount = 2000.0,
            driverName = "Jolayemi Muinat",
            tripStatus = "SUCCESSFUL",
            createdAt = "20 Apr, 17:03"
        ),

        TripHistory(
            id = 11,
            address = "Grants, New Mexico(NM), 87020",
            amount = 2000.0,
            driverName = "Zainab Tolulope",
            tripStatus = "SUCCESSFUL",
            createdAt = "20 Apr, 17:03"
        ),

        TripHistory(
            id = 12,
            address = "Brent, Alabama(AL), 35034",
            amount = 2000.0,
            driverName = "Olaide Ndukwu",
            tripStatus = "CANCELLED",
            createdAt = "20 Apr, 17:03"
        ),

        TripHistory(
            id = 13,
            address = "Georgetown, Delaware(DE), 19947",
            amount = 2000.0,
            driverName = "Lolade Ebubechukwu",
            tripStatus = "SUCCESSFUL",
            createdAt = "20 Apr, 17:03"
        ),

        TripHistory(
            id = 14,
            address = "Rich Hill, Missouri(MO), 64779",
            amount = 2000.0,
            driverName = "Isioma Maryjane",
            tripStatus = "CANCELLED",
            createdAt = "20 Apr, 17:03"
        ),

        TripHistory(
            id = 15,
            address = "Crown Point, Indiana(IN), 46307",
            amount = 2000.0,
            driverName = "Johnson Olubukola",
            tripStatus = "SUCCESSFUL",
            createdAt = "20 Apr, 17:03"
        ),

        TripHistory(
            id = 16,
            address = "Missoula, Montana(MT), 59801",
            amount = 2000.0,
            driverName = "Bankole Peter",
            tripStatus = "SUCCESSFUL",
            createdAt = "20 Apr, 17:03"
        ),

        TripHistory(
            id = 17,
            address = "Westfield, New Jersey(NJ), 07090",
            amount = 2000.0,
            driverName = "Jadesola Makuachukwu",
            tripStatus = "CANCELLED",
            createdAt = "20 Apr, 17:03"
        ),

        TripHistory(
            id = 18,
            address = "Raleigh, North Carolina(NC), 27617",
            amount = 2000.0,
            driverName = "Johnson Ajakaiye",
            tripStatus = "SUCCESSFUL",
            createdAt = "20 Apr, 17:03"
        ),

        TripHistory(
            id = 19,
            address = "Starkville, Mississippi(MS), 39759",
            amount = 2000.0,
            driverName = "Titi Ehigiator ",
            tripStatus = "CANCELLED",
            createdAt = "20 Apr, 17:03"
        ),

        TripHistory(
            id = 20,
            address = "Cleveland, New York(NY), 13042",
            amount = 2000.0,
            driverName = "Azubuike Adaobi",
            tripStatus = "SUCCESSFUL",
            createdAt = "20 Apr, 17:03"
        )


    ).sortedByDescending { it.id }


    val DummyRecentLocations: List<RecentLocation> = listOf(
        RecentLocation(
            id = 1,
            isHouseAddress = false,
            isWorkAddress = true,
            address = "Flushing, Michigan(MI), 48433",
            label = "Michigan"
        ), RecentLocation(
            id = 2,
            isHouseAddress = true,
            isWorkAddress = false,
            address = "New Bedford, Massachusetts(MA), 02740",
            label = "Massachusetts"
        ), RecentLocation(
            id = 3,
            isHouseAddress = false,
            isWorkAddress = false,
            address = "Stillwater, Oklahoma(OK), 74074",
            label = "Oklahoma"
        ), RecentLocation(
            id = 4,
            isHouseAddress = false,
            isWorkAddress = false,
            address = "Camden Wyoming, Delaware(DE), 199344",
            label = "Delaware"
        ), RecentLocation(
            id = 5,
            isHouseAddress = false,
            isWorkAddress = false,
            address = "Shannon, Illinois(IL), 61078",
            label = "Illinois"
        ), RecentLocation(
            id = 5,
            isHouseAddress = false,
            isWorkAddress = false,
            address = "Rembert, South Carolina(SC), 29128",
            label = "Carolina"
        ), RecentLocation(
            id = 6,
            isHouseAddress = false,
            isWorkAddress = false,
            address = "Summer Sweet Cv Cordova, Tennessee(TN), 38016",
            label = "Tennessee"
        ), RecentLocation(
            id = 7,
            isHouseAddress = false,
            isWorkAddress = false,
            address = "Beloit, Wisconsin(WI), 53511",
            label = "Wisconsin"
        ), RecentLocation(
            id = 8,
            isHouseAddress = false,
            isWorkAddress = false,
            address = "Beloit, Wisconsin(WI), 53511",
            label = "Wisconsin"
        ), RecentLocation(
            id = 9,
            isHouseAddress = false,
            isWorkAddress = false,
            address = "Beloit, Wisconsin(WI), 53511",
            label = "Wisconsin"
        ), RecentLocation(
            id = 10,
            isHouseAddress = false,
            isWorkAddress = false,
            address = "Beloit, Wisconsin(WI), 53511",
            label = "Wisconsin"
        )
    ).sortedBy { it.id }


    val DummyAtmCards: List<DebitCard> = listOf(

        DebitCard(
            id = 1,
            pan = "51610662 82918601",
            expiry = "09/26",
            cardHolderName = "Augustine Paris",
            isDefault = true
        ),

        DebitCard(
            id = 2,
            pan = "4644848022934828",
            expiry = "06/26",
            cardHolderName = "Stephania Kazmierczak",
            isDefault = false
        ),

        DebitCard(
            id = 3,
            pan = "4725079677434185",
            expiry = "08/26",
            cardHolderName = "Samatha Schroeder",
            isDefault = false
        )

    )

    val DummyWallet: Wallet = Wallet(balance = 13_000.0, currency = "NGN")
}
