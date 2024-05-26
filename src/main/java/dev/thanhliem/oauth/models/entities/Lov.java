package dev.thanhliem.oauth.models.entities;

import dev.thanhliem.oauth.models.jsonb.LovDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lov extends Entities {
    private String name;
    private LovDetails details;
}
