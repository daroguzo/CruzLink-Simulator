package kr.co.direa.cruzlinksimulator.constant;

import lombok.Getter;

@Getter
public enum Encoding {
    EUCKR("EUC-KR"),
    KSC5601("KSC5601"),
    UTF8("UTF-8")
    ;

    Encoding(String value) {
        this.value = value;
    }

    private final String value;

}
