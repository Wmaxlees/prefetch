public class Operation
{
	private int _opc; 
	private String _rs;
	private String _rt;
	private String _rd;
	
	public Operation (int opc, String rs, String rt, String rd)
	{
		this._opc = opc;
		this._rs = rs;
		this._rt = rt;
		this._rd = rd;
	}
	
	public int opc()
	{
			return this._opc;
	}
	
	public String rs()
	{
		return this._rs;
	}
	
	public String rt()
	{
		return this._rt;
	}
	
	public String rd()
	{
		return this._rd;
	}
	
}