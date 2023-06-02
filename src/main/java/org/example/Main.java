package org.example;

import core.Client;
import core.ClientService;
import core.Contract;
import core.Services.ContractService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        ContractService contractService = new ContractService();

        // Criação de clientes
        Client client1 = new Client(1, 11929826303L, "Ana Zaira");
        Client client2 = new Client(2, 26752965030L, "Beatriz Yana");

        // Criação de contratos
        Contract contract1 = new Contract(1, "Contrato por tempo determinado", LocalDate.of(2023, 5, 21), 1);
        Contract contract2 = new Contract(2, "Contrato por tempo indeterminado", LocalDate.of(2023, 5, 1), 1);
        Contract contract3 = new Contract(3, "Contrato de trabalho eventual", LocalDate.of(2023, 5, 26), 1);
        Contract contract4 = new Contract(4, "Contrato de estágio", LocalDate.of(2023, 10, 15), 2);
        Contract contract5 = new Contract(5, "Contrato de experiência", LocalDate.of(2023, 9, 16), 2);
        Contract contract6 = new Contract(6, "Contrato de teletrabalho", LocalDate.of(2023, 8, 17), 2);
        Contract contract7 = new Contract(7, "Contrato intermitente", LocalDate.of(2023, 7, 15), 2);

        // Inserir clientes no banco de dados
        clientService.postClient(client1);
        clientService.postClient(client2);

        // Inserir contratos no banco de dados
        contractService.postContract(contract1, client1.getId());
        contractService.postContract(contract2, client1.getId());
        contractService.postContract(contract3, client1.getId());
        contractService.postContract(contract4, client2.getId());
        contractService.postContract(contract5, client2.getId());
        contractService.postContract(contract6, client2.getId());
        contractService.postContract(contract7, client2.getId());

        // Obter um cliente pelo ID
        long clientId = 1;
        Client retrievedClient = clientService.getClient(clientId);
        if (retrievedClient != null) {
            System.out.println("Cliente obtido: " + retrievedClient.getName());
        } else {
            System.out.println("Cliente não encontrado");
        }

        // Obter todos os clientes
        Map<String, Object> allClientsResult = clientService.getAllClients();
        int allClientsStatus = (int) allClientsResult.get("status");
        if (allClientsStatus == 200) {
            @SuppressWarnings("unchecked")
            List<Client> clientsList = (List<Client>) allClientsResult.get("body");
            System.out.println("Lista de clientes:");
            for (Client client : clientsList) {
                System.out.println("- " + client.getName());
            }
        } else {
            System.out.println("Erro ao obter lista de clientes");
        }

        // Obter um contrato pelo ID
        long contractId = 1;
        Contract retrievedContract = contractService.getContract(contractId);
        if (retrievedContract != null) {
            System.out.println("Contrato obtido: " + retrievedContract.getRedacao());
        } else {
            System.out.println("Contrato não encontrado");
        }

        // Obter todos os contratos
        List<Contract> contractsList = contractService.getAllContracts();
        if (!contractsList.isEmpty()) {
            System.out.println("Lista de contratos:");
            for (Contract contract : contractsList) {
                System.out.println("- " + contract.getRedacao());
            }
        } else {
            System.out.println("Nenhum contrato encontrado");
        }

        // Atualizar um cliente existente
        long updatedClientId = 1;
        Client updatedClient = new Client(updatedClientId, 11929826303L, "Ana Zaira Atualizado");
        clientService.updateClient(updatedClient, updatedClientId);

        // Excluir um contrato existente
        long deletedContractId = 1;
        contractService.deleteContract(deletedContractId);

        // Obter todos os clientes novamente para verificar as atualizações
        allClientsResult = clientService.getAllClients();
        allClientsStatus = (int) allClientsResult.get("status");
        if (allClientsStatus == 200) {
            @SuppressWarnings("unchecked")
            List<Client> updatedClientsList = (List<Client>) allClientsResult.get("body");
            System.out.println("Lista de clientes atualizada:");
            for (Client client : updatedClientsList) {
                System.out.println("- " + client.getName());
            }
        } else {
            System.out.println("Erro ao obter lista de clientes atualizada");
        }
    }
}