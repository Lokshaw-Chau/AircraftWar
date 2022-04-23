package edu.hitsz.supply;

/**
 * SupplyFactory
 *
 * @author Lau-Shaw Chau
 * @date 2022/04/04
 */
public interface SupplyFactory {
    /**
     * 创建一个补给对象
     *
     * @param locationX 补给的x坐标
     * @param locationY 补给的y坐标
     * @return AbstractSupply 补给对象
     */
    public AbstractSuppply creatSupply(int locationX, int locationY);
}
