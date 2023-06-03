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

        System.out.println("Inserindo clientes no banco de dados...");

        // Criação de clientes
        Client client1 = new Client(1, 11929826303L, "Ana Zaira");
        Client client2 = new Client(2, 26752965030L, "Beatriz Yana");

        // Inserir clientes no banco de dados
        Map<String, Object> postClientResult1 = clientService.postClient(client1);
        Map<String, Object> postClientResult2 = clientService.postClient(client2);

        // Verificar o resultado da inserção dos clientes
        int client1Status = (int) postClientResult1.get("status");
        int client2Status = (int) postClientResult2.get("status");
        if (client1Status == 200) {
            System.out.println("Cliente 1 inserido: " + client1.getName());
        } else {
            System.out.println("Erro ao inserir Cliente 1");
        }
        if (client2Status == 200) {
            System.out.println("Cliente 2 inserido: " + client2.getName());
        } else {
            System.out.println("Erro ao inserir Cliente 2");
        }

        System.out.println("Inserindo contratos no banco de dados...");

        // Criação de contratos
        Contract contract1 = new Contract(1, "Contrato por tempo determinado", LocalDate.of(2023, 5, 21), 1);
        Contract contract2 = new Contract(2, "Contrato por tempo indeterminado", LocalDate.of(2023, 5, 1), 1);
        Contract contract3 = new Contract(3, "Contrato de trabalho eventual", LocalDate.of(2023, 5, 26), 1);
        Contract contract4 = new Contract(4, "Contrato de estágio", LocalDate.of(2023, 10, 15), 2);
        Contract contract5 = new Contract(5, "Contrato de experiência", LocalDate.of(2023, 9, 16), 2);
        Contract contract6 = new Contract(6, "Contrato de teletrabalho", LocalDate.of(2023, 8, 17), 2);
        Contract contract7 = new Contract(7, "Contrato intermitente", LocalDate.of(2023, 7, 15), 2);

        // Inserir contratos no banco de dados
        Map<String, Object> postContractResult1 = contractService.postContract(contract1, client1.getId());
        Map<String, Object> postContractResult2 = contractService.postContract(contract2, client1.getId());
        Map<String, Object> postContractResult3 = contractService.postContract(contract3, client1.getId());
        Map<String, Object> postContractResult4 = contractService.postContract(contract4, client2.getId());
        Map<String, Object> postContractResult5 = contractService.postContract(contract5, client2.getId());
        Map<String, Object> postContractResult6 = contractService.postContract(contract6, client2.getId());
        Map<String, Object> postContractResult7 = contractService.postContract(contract7, client2.getId());
                // Verificar o resultado da inserção dos contratos
        int contract1Status = (int) postContractResult1.get("status");
        int contract2Status = (int) postContractResult2.get("status");
        int contract3Status = (int) postContractResult3.get("status");
        int contract4Status = (int) postContractResult4.get("status");
        int contract5Status = (int) postContractResult5.get("status");
        int contract6Status = (int) postContractResult6.get("status");
        int contract7Status = (int) postContractResult7.get("status");

        if (contract1Status == 200) {
            System.out.println("Contrato 1 inserido: " + contract1.getRedacao());
        } else {
            System.out.println("Erro ao inserir Contrato 1");
        }
        if (contract2Status == 200) {
            System.out.println("Contrato 2 inserido: " + contract2.getRedacao());
        } else {
            System.out.println("Erro ao inserir Contrato 2");
        }
        if (contract3Status == 200) {
            System.out.println("Contrato 3 inserido: " + contract3.getRedacao());
        } else {
            System.out.println("Erro ao inserir Contrato 3");
        }
        if (contract4Status == 200) {
            System.out.println("Contrato 4 inserido: " + contract4.getRedacao());
        } else {
            System.out.println("Erro ao inserir Contrato 4");
        }
        if (contract5Status == 200) {
            System.out.println("Contrato 5 inserido: " + contract5.getRedacao());
        } else {
            System.out.println("Erro ao inserir Contrato 5");
        }
        if (contract6Status == 200) {
            System.out.println("Contrato 6 inserido: " + contract6.getRedacao());
        } else {
            System.out.println("Erro ao inserir Contrato 6");
        }
        if (contract7Status == 200) {
            System.out.println("Contrato 7 inserido: " + contract7.getRedacao());
        } else {
            System.out.println("Erro ao inserir Contrato 7");
        }

        System.out.println("Realizando consultas no banco de dados...");

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

        System.out.println("Atualizando um cliente existente...");

        // Atualizar um cliente existente
        long updatedClientId = 1;
        Client updatedClient = new Client(updatedClientId, 11929826303L, "Ana Zaira Atualizado");
        Map<String, Object> updateClientResult = clientService.updateClient(updatedClient, updatedClientId);
        int updateClientStatus = (int) updateClientResult.get("status");
        if (updateClientStatus == 200) {
            System.out.println("Cliente atualizado com sucesso");
        } else {
            System.out.println("Erro ao atualizar o cliente");
        }

        System.out.println("Excluindo um contrato existente...");

        // Excluir um contrato existente
        long deletedContractId = 1;
        Map<String, Object> deleteContractResult = contractService.deleteContract(deletedContractId);
        int deleteContractStatus = (int) deleteContractResult.get("status");
        if (deleteContractStatus == 200) {
            System.out.println("Contrato excluído com sucesso");
        } else {
            System.out.println("Erro ao excluir o contrato");
        }

        System.out.println("Obtendo todos os clientes novamente para verificar as atualizações...");

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
