package org.ming.biz;

import java.util.*;
import java.util.stream.Collectors;

public class Interview {
    /**
     * Question:
     * When the user visits the shopping cart page, we need to show the products that the user added to the cart.
     *
     * The product object structure is as follows:
     * Product:{id,groupInfo,price}
     *
     * We need to sort the products according to the following rules:
     * 1. Products are grouped according to 'groupInfo', and each 'groupInfo' group is ASC sorted according to the minimum ID of the products within the group;
     * 2. The products within the same 'groupInfo' group are grouped according to benefit.benefitType, and each benefitType group is DESC sorted according to benefit.benefitValue;
     * 	Note: We need to call another system to get the benefit information, which takes 200ms.
     * 3. The products in the same 'groupInfo','benefitType','benefitValue' group is sorted by 'price' ASC,'id' DESC
     *
     * We don't have to do it exactly as 1->2->3, we just have to get the final result right.
     *
     *
     * Requirements:
     * Complete the sorting logic correctly.
     * Optional requirements:
     * 1. Reduce the overall time of sorting.
     * 2. Reduce the computational complexity of sorting.
     * */

    /**
     * sample:
     * Product:{id,groupInfo,price}
     * Input:
     * [
     * {1,group1,521},
     * {13,group1,521},
     * {4,group5,454},
     * {11,group1,501},
     * {41,group2,555},
     * {28,group5,623}
     * ]
     * <p>
     * STEP1:
     * {
     * {1,group1,521},
     * {13,group1,521},
     * {11,group1,501},
     * {4,group5,454},
     * {28,group5,623},
     * {41,group2,555}
     * }
     * <p>
     * <p>
     * STEP2:
     * queryBenefit:
     * {id,group,price,benefitType,benefitValue}
     * [
     * {1,group1,521,benefitType1,100},
     * {13,group1,521,benefitType3,111},
     * {11,group1,501,benefitType3,125},
     * {4,group5,454,benefitType1,220},
     * {28,group5,623,benefitType2,345},
     * {41,group2,555,benefitType4,222}
     * ]
     * sort:
     * [
     * {13,group1,521,benefitType3,111},
     * {11,group1,501,benefitType3,125},
     * {1,group1,521,benefitType1,100},
     * {28,group5,623,benefitType2,345},
     * {4,group5,454,benefitType1,220},
     * {41,group2,555,benefitType4,222}
     * ]
     * <p>
     * STEP3:
     * [
     * {11,group1,501,benefitType3,125},
     * {13,group1,521,benefitType3,111},
     * {1,group1,521,benefitType1,100},
     * {28,group5,623,benefitType2,345},
     * {4,group5,454,benefitType1,220},
     * {41,group2,555,benefitType4,222}
     * ]
     */

    /**
     * TODO: add sorting logic and return sorted result
     * NOTE: need call Benefit.queryBenefit(Product product) to fill the benefitType and benefitValue field.
     */
    public static List<Product> sortProductList(List<Product> inputs) {
        //TODO


        return null;

    }

    public static void main(String args[]) {
//        List<Product> testProducts=buildFixedProductList(); //This returns the same value as the sample input
//        printResult(testProducts);
        long totalTime = 0;
        int totalSortedItems = 0;

        for (int i = 0; i < 5; i++) {
            int size = new Random().nextInt(13) + 1;
            totalSortedItems = totalSortedItems + size;
            List<Product> inputs = buildRandomProductList(size);

            //print inputs
            System.out.println("input:");
            printResult(inputs);


            long beginTime = System.currentTimeMillis();
            //please fill the sortProductList logic
//            List<Product> result = sortProductList(inputs);
//
//            //print result
//            System.out.println("result:");
//            totalTime = totalTime + System.currentTimeMillis() - beginTime;
//            printResult(result);
//            System.out.println("---------------------end-----------------------");

            // Map<String, Optional<Product>> collect = inputs.parallelStream().collect(Collectors.groupingBy(Product::getGroupInfo, Collectors.minBy(Comparator.comparing(Product::getId))));

            TreeMap<String, Product> treeMap = inputs.parallelStream().collect(Collectors.groupingBy(Product::getGroupInfo, TreeMap::new, Collectors.collectingAndThen(Collectors.toList(), item -> {
                MoreProducts moreProducts = new MoreProducts();
                Product minProduct = item.stream().min(Comparator.comparing(Product::getId)).orElseThrow(() -> new RuntimeException("异常"));
                moreProducts.setProducts(item);
                moreProducts.setMinProduct(minProduct);
                return minProduct;
            })));

            treeMap.entrySet();

//            Map<String, List<Product>> collect = inputs.parallelStream().collect(Collectors.groupingBy(Product::getGroupInfo,
//                    Collectors.collectingAndThen(Collectors.toList(), item -> item.stream().sorted(Comparator.comparing(Product::getId)).collect(Collectors.toList())
//                    )));
//            System.out.println(collect);
        }
        // System.out.println("totalSortedItems=" + totalSortedItems + ",cost time=" + totalTime);
    }

    static class MoreProducts{
        Product minProduct;
        List<Product> products;

        public MoreProducts() {
        }

        public MoreProducts(Product minProduct, List<Product> products) {
            this.minProduct = minProduct;
            this.products = products;
        }

        public Product getMinProduct() {
            return minProduct;
        }

        public void setMinProduct(Product minProduct) {
            this.minProduct = minProduct;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }
    }


    static class Product {
        long id;
        String groupInfo;
        int price;
        String benefitType;
        int benefitValue;

        Product(long id, String groupInfo, int price) {
            this.id = id;
            this.groupInfo = groupInfo;
            this.price = price;
        }

        Product(long id, String groupInfo, int price, String benefitType, int benefitValue) {
            this.id = id;
            this.groupInfo = groupInfo;
            this.price = price;
            this.benefitType = benefitType;
            this.benefitValue = benefitValue;
        }

        String getGroupInfo() {
            return groupInfo;
        }

        public long getId() {
            return id;
        }
    }


    static class Benefit {
        int benefitValue;
        String benefitType;

        static final String[] benefitTypes = new String[]{"benefitType1", "benefitType2", "benefitType3", "benefitType4", "benefitType5"};

        Benefit(int benefitValue, String benefitType) {
            this.benefitValue = benefitValue;
            this.benefitType = benefitType;
        }


        /**
         * just mock the call latency and return mock result
         * <p>
         * don't care the detail implementation
         */
        public static Benefit queryBenefit(Product product) {
            try {
                //query Benefit need call other systems,so need 200ms.
                Thread.sleep(200);
                return getBenefit(product.price);
            } catch (Exception e) {
                e.printStackTrace();
                return getBenefit(product.price);
            }
        }

        private static Benefit getBenefit(int price) {
            return new Benefit(new Random(111).nextInt(price), benefitTypes[new Random().nextInt(benefitTypes.length)]);
        }
    }

    // just to build the inputs data, don't care
    public static List<Product> buildRandomProductList(int size) {
        Random random = new Random(4323);
        Random priceRandom = new Random(2322);
        List<Random> randoms = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            randoms.add(priceRandom);
        }
        randoms.add(new Random(2322));
        randoms.add(new Random(4323));
        List<Product> ProductList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            ProductList.add(new Product(Math.abs(random.nextLong()), "group" + random.nextInt(5), randoms.get(random.nextInt(9)).nextInt(30000)));
        }
        return ProductList;

    }

    // just to build the sample data, help to debug
    public static List<Product> buildFixedProductList() {
        List<Product> ProductList = new LinkedList<Product>();
        ProductList.add(new Product(1, "group1", 521, "benefitType1", 200));
        ProductList.add(new Product(13, "group1", 521, "benefitType3", 111));
        ProductList.add(new Product(4, "group5", 454, "benefitType1", 220));
        ProductList.add(new Product(11, "group1", 501, "benefitType3", 125));
        ProductList.add(new Product(41, "group2", 555, "benefitType4", 222));
        ProductList.add(new Product(28, "group5", 623, "benefitType2", 345));
        return ProductList;

    }

    // print the result,help to debug
    static void printResult(List<Product> products) {
        System.out.println("{");
        if (products != null) {
            products.stream().forEach(product -> {
                System.out.println("[" + product.id + "," + product.groupInfo + "," + product.price + "," + product.benefitType + "," + product.benefitValue + "],");
            });
        }

        System.out.println("}");
    }

}