package dataGenerator.user;

import dataGenerator.organisation.OrganisationData;
import organisation.Locations.City;
import organisation.offering.OfferingItem;
import organisation.user.Client;
import organisation.user.Instructor;
import organisation.user.UnderageClient;

import java.util.ArrayList;
import java.util.List;

public class ClientData {


    public static ArrayList<Client> generateClients() {

        ArrayList<Client> clients = new ArrayList<>();

       clients.add(new Client("client1","client"));
       clients.add(new Client("client2","client" ));
       clients.add(new UnderageClient("child1","child",clients.get(0)));

        return clients;

    }
}
