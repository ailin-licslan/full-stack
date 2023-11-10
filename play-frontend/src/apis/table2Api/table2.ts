import {DownOutlined, SmileOutlined} from '@ant-design/icons-vue';

const columns = [
    {
        dataIndex: 'name',
        key: 'name',
        slots: { title: 'customTitle', customRender: '姓名' },
        id: 'id'
    },
    {
        title: '年龄',
        dataIndex: 'age',
        key: 'age',
        id: 'id'
    },
    {
        title: '地址',
        dataIndex: 'address',
        key: 'address',
        id: 'id'

    },
    {
        title: '标签',
        key: 'tags',
        dataIndex: 'tags',
        slots: { customRender: 'tags' },
        id: 'id'
    },
    {
        title: '创建时间',
        key: 'createTime',
        dataIndex: 'createTime',
        id: 'id'
    },
    {
        title: '操作',
        key: 'action',
        slots: { customRender: 'action' },
        id: 'id'
    },
];

const data = [
    // {
    //     key: '1',
    //     name: '小红',
    //     age: 32,
    //     address: '武汉市江汉区',
    //     time: '2023-10-26 10:07:32',
    //     tags: ['nice', 'developer'],
    // },
    //
    // {
    //     key: '2',
    //     name: '小绿',
    //     age: 42,
    //     address: '武汉市江汉区',
    //     time: '2023-10-26 10:07:32',
    //     tags: ['loser'],
    // },
    //
    // {
    //     key: '3',
    //     name: '小白',
    //     age: 32,
    //     address: '上海市静安去',
    //     time: '2023-10-26 10:07:32',
    //     tags: ['cool', 'teacher'],
    // },
];

export default {
    setup() {
        return {
            data,
            columns,
        };
    },
    components: {
        SmileOutlined,
        DownOutlined,
    },
};