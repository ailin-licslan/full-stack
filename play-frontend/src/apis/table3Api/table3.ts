import {ref, Ref} from "vue";
import {DownOutlined, SmileOutlined} from "@ant-design/icons-vue";

const dataSource: Ref<DataItem[]> = ref([
    {
        key: '0',
        name: 'Edward King 0',
        age: 32,
        address: 'London, Park Lane no. 0',
    },
    {
        key: '1',
        name: 'Edward King 1',
        age: 32,
        address: 'London, Park Lane no. 1',
    },
]);


interface DataItem {
    key: string;
    name: string;
    age: number;
    address: string;
}


export default {
    setup() {
        return {
            dataSource,
        };
    },
    components: {
        SmileOutlined,
        DownOutlined,
    },
};