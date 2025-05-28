package virtualhealth.mapper;

import org.springframework.stereotype.Component;
import virtualhealth.dto.ClientUpdateDTO;
import virtualhealth.model.Client;

@Component
public class ClientMapper {
    public ClientUpdateDTO toDTO(Client client) {
        ClientUpdateDTO dto = new ClientUpdateDTO();
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setEmail(client.getEmail());
        dto.setAddress(client.getAddress());
        dto.setSex(client.getSex());
        dto.setTel(client.getTel());
        dto.setDateBirth(client.getDateBirth() != null ? client.getDateBirth().toString() : null);
        return dto;
    }
}
