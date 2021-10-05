package br.com.master.enums;

public enum MesesEnum {
    // Janeiro(1), Fevereiro(2), Março(3), Abril(4), Maio(5), Junho(6),
    // Julho(7), Agosto(8), Setembro(9), Outubro(10), Novembro(11),
    // Dezembro(12);

    Janeiro(1, "Janeiro"), Fevereiro(2, "Fevereiro"), Março(3, "Março"), Abril(
	    4, "Abril"), Maio(5, "Maio"), Junho(6, "Junho"), Julho(7, "Julho"), Agosto(
	    8, "Agosto"), Setembro(9, "Setembro"), Outubro(10, "Outubro"), Novembro(
	    11, "Novembro"), Dezembro(12, "Dezembro");
    
    private final int nroMes;
    private final String descricao;

    private MesesEnum(int nroMes, String descricao) {
	this.descricao = descricao;
	this.nroMes = nroMes;
    }

    public int getNroMes() {
	return nroMes;
    }

    public static void main(String[] args) {
	MesesEnum names[] = MesesEnum.values();
	for (MesesEnum mes : names) {
	    System.out.println("Mes " + mes.getNroMes() + ";;"
		    + mes.getDescricao()+"--"+mes);
	}
    }

    public String getDescricao() {
	return descricao;
    }

}