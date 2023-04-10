module jmp.cloud.service.imp {
    requires jmp.service.api;
    provides org.service.service.Service  with org.serv.impl.ServiceImpl;
}