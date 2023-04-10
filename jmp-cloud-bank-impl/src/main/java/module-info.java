module jmp.cloud.bank.impl {
    requires jmp.bank.api;
    provides org.bank.api.Bank with org.cloud.impl.CloudBank;

}