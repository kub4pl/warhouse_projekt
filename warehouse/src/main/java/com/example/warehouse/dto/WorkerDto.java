package com.example.warehouse.dto;

public class WorkerDto {

    private String name;
    private String surname;
    private String PESEL;
    private String accountNumber;
    private String workerType;

    private String NIP;

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    @Override
    public String toString() {
        return "WorkerDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", PESEL='" + PESEL + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", workerType='" + workerType + '\'' +
                ", NIP='" + NIP + '\'' +
                '}';
    }
}
