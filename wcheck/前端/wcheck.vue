<!--
descreption:笔记本列表界面
creater: lewl
time:20190820
-->
<template>
    <!-- 界面按钮区域-->
    <div class="containers-container">
        <div class="filter-container">
            考勤状态:<el-select v-model="tableQuery.kqzt" placeholder="请填写状态">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.value"
                :value="item.value"
                :disabled="item.disabled"
            ></el-option>
            </el-select>
            <el-button style="margin-left: 10px;" @click="getList()" type="primary"><i class="el-icon-search"></i>查询</el-button>
            <el-form id="uploadForm" enctype="multipart/form-data">
                <el-input type="file" name="filename" class="fileinput btn-lg"/>
                <el-button type="button" @click="onUpload" class="fileinput_button btn btn-lg btn-success">导入</el-button>
            </el-form>
            <!--el-input v-model="tableQuery.id" @keyup.enter.native="handleFilter" style="width: 200px;" placeholder="ID"></el-input>
            <el-button @click="deleteRecord(deletedForm.id)" type="primary"><i class="el-icon-delete"></i>删除</el-button-->
            <el-button @click="showDialog('xj')" type="primary"><i class="el-icon-info"></i>新建</el-button>
            <el-button @click="showDialog('xg',createdForm)" type="primary"><i class="el-icon-info"></i>编辑</el-button>
            <el-button @click="getExcel()" type="primary"><i class="el-icon-info"></i>导出</el-button>
            <el-button v-on:click="cqkh(tableT)" type="primary"><i class="el-icon-info"></i>出勤考核</el-button>
        </div>
        <!-- 界面列表区域-->
        <el-table :data="tableT" @row-click="handlecellclick" v-loading.body="tableLoading" element-loading-text="拼命加载中" stripe border fit highlight-current-row style="width: 100%">
            <el-table-column label="编号" prop="id" align="center"></el-table-column>
            <el-table-column label="日期时间" prop="time" align="center"></el-table-column>
            <el-table-column label="员工编号" prop="usercode" align="center"></el-table-column>
            <el-table-column label="员工姓名" prop="username" align="center"></el-table-column>
            <el-table-column label="上班时间" prop="stime" align="center"></el-table-column>
            <el-table-column label="下班时间" prop="etime" align="center"></el-table-column>
            <el-table-column label="考勤状态" prop="kqzt" align="center"></el-table-column>
            <el-table-column label="出勤说明" prop="outdesc" align="center"><template slot-scope="tableT"><div :style="{'color':'red'}">{{tableT.row.outdesc}} <el-button @click="showDialog('cq',createdForm)" type="primary"><i class="el-icon-info"></i>出勤说明</el-button></div></template> </el-table-column>
            <el-table-column label="考勤状态调整" prop="justdoit" align="center"><el-button @click="showDialog('kq',createdForm)" type="primary"><i class="el-icon-info"></i>考勤状态调整</el-button>
            </el-table-column>
            <el prop="creater" align="center"></el>
            <el prop="creattime" align="center"></el>
        </el-table>
        <el-dialog :title="formTitle" :visible.sync="formVisible" >
            <el-form class="small-space" :model="createdForm" label-position="left" label-width="70px" style='width: 60%; margin-left:0px;'>
                <el-form-item label="编号">
                    <el-input v-model="createdForm.id" placeholder="编号" disabled="disabled"></el-input>
                </el-form-item>
                <el-form-item label="日期时间">
                    <el-input v-model="createdForm.time" placeholder="请填写标题" v-bind:disabled="booleankg==3||booleankg==4"></el-input>
                </el-form-item>
                <el-form-item label="员工编号">
                    <el-input v-model="createdForm.usercode" placeholder="请填写内容" v-bind:disabled="booleankg==3||booleankg==4"></el-input>
                </el-form-item>
                <el-form-item label="员工姓名">
                    <el-input v-model="createdForm.username" placeholder="请填写状态" v-bind:disabled="booleankg==3||booleankg==4"></el-input>
                </el-form-item>
                <el-form-item label="上班时间">
                    <el-input v-model="createdForm.stime" placeholder="请填写状态" v-bind:disabled="booleankg==3||booleankg==4"></el-input>
                </el-form-item>
                <el-form-item label="下班时间">
                    <el-input v-model="createdForm.etime" placeholder="请填写状态" v-bind:disabled="booleankg==3||booleankg==4"></el-input>
                </el-form-item>
                <el-form-item label="考勤状态">
                    <el-select v-model="createdForm.kqzt" placeholder="请填写状态" v-bind:disabled="booleankg==3">
                        <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.value"
                        :value="item.value"
                        :disabled="item.disabled"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="出勤说明">
                    <el-input v-model="createdForm.outdesc" placeholder="请填写状态" v-bind:disabled="booleankg==4"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="formVisible = false">取 消</el-button>
                <el-button type="primary" :loading="formSubmiting" @click="commitForm">确 认</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import { confirmBox } from 'utils/message';
    export default {
        data() {
            return {
                tableQuery: {
                    limit: 10,
                    currentPage: 1,
                    kqzt: null,
                },
                booleankg:1,
                total: null,
                tableT: [],
                tableLoading: true,
                formTitle: null,
                formTitle1: null,
                formVisible: false,
                formSubmiting: false,
                createdForm: {
                    id: null,
                    time: null,
                    usercode: null,
                    username: null,
                    stime: null,
                    etime: null,
                    outdesc: null,
                    kqzt: null,
                },
                deletedForm: {
                    id: null
                },
                options:[{
                value:'正常出勤'},{
                value:  '迟到早退'},{
                value:  '旷工'},{
                value:  '病假'},{
                value:  '事假'},{
                value:  '年假'},{
                value:  '婚假'},{
                value:  '丧假'},{
                value:  '其他'
            }],
                };
        },
        mounted() {
            this.getList();
        },
        methods: {
            getList() {//查询方法
                this.tableLoading = true;
                this.$api.system.wcheck.cx({
                    data: this.tableQuery
                }).then(response => {
                    this.total = response.data.data.total;
                    this.tableT = response.data.data.cx;
                    this.tableLoading = false;
                    console.log(this.tableT)
                }).catch(error => {
                    this.tableLoading = false;
                    console.log(error);
                });
            },
            handlecellclick(val) {
                this.deletedForm.id=val.id
                this.createdForm=val
            },
            deleteRecord(id) {
                confirmBox('考勤信息').then(() => {
                    this.$api.system.wcheck.sc({
                        data: this.deletedForm
                    }).then(response => {
                        this.$notify({
                            title: '成功',
                            message: '删除成功',
                            type: 'success',
                            duration: 1500,
                        });
                        this.getList();
                        console.log(response);
                    }).catch(error => {
                        this.formSubmiting = false;
                        this.$notify({
                            title: '错误',
                            message: error,
                            type: 'error',
                            duration: 3000,
                        });
                        console.log(error);
                    });
                });
            },
            //清空dilog
            clearDialog() {
                this.createdForm.id = null;
                this.createdForm.time = null;
                this.createdForm.usercode = null;
                this.createdForm.username = null;
                this.createdForm.stime = null;
                this.createdForm.etime = null;
                this.createdForm.outdesc = null;
                this.createdForm.kqzt = null;
            },
            showDialog(type,row){//调用显示dilog
                console.log(type);
                this.booleankg=1;
                this.formVisible = true;//显示dilog
                if (type == 'xj') {
                    this.clearDialog();//调用清空
                    this.formTitle = "新增笔记本信息";
                }
                else if (type == 'cq') {
                    this.booleankg=3;
                    this.formTitle = "编辑出勤说明";
                    if(row.id==null){
                        alert('未选中记录')
                        this.formVisible = false;
                    }
                }
                else if (type == 'kq') {
                    this.booleankg=4;
                    this.formTitle = "编辑考勤状态";
                    if(row.id==null){
                        alert('未选中记录')
                        this.formVisible = false;
                    }
                }else {//判断编辑
                    this.formTitle = "修改笔记本信息";
                    if(row.id==null){
                        alert('未选中记录')
                        this.formVisible = false;
                    }
                }
                this.getList();
            },
            commitForm() {
                this.formSubmiting = true;
                //this.createdForm.creattime=new Date().getFullYear()+'-'+new Date().getMonth()+'-'+new Date().getDay()
                this.$api.system.wcheck.save({
                    data: this.createdForm
                }).then(response => {
                    this.formSubmiting = false;
                    this.formVisible = false;
                    this.$notify({
                        title: '成功',
                        message: '保存成功',
                        type: 'success',
                        duration: 1500,
                    });
                    this.booleankg=1;
                    this.getList();
                    console.log(response);
                }).catch(error => {
                    this.formSubmiting = false;
                    this.$notify({
                        title: '错误',
                        message: error,
                        type: 'error',
                        duration: 3000,
                    });
                    this.booleankg=1;
                    console.log(error);
                });
            },onUpload: function (e) {
                this.$api.system.wcheck.import({
                    data: new FormData($('#uploadForm')[0]),
                }).then(response => {
                    this.formSubmiting = false;
                    this.formVisible = false;
                    this.$notify({
                        title: '成功',
                        message: '导入成功',
                        type: 'success',
                        duration: 1500,
                    });
                    this.getList();
                    console.log(response.data)
                }).catch(error => {
                    console.log(error);
                });
            },getExcel() {
                this.$api.system.wcheck.export({
                    data: this.tableQuery,
                }).then(response => {
                     require.ensure([], () => {
                         console.log("测试1");
                         const { export_json_to_excel } = require('../../vendor/Export2Excel')
                         const tHeader = ['编号', '日期时间','员工编号','员工姓名','上班时间','下班时间','出勤说明','考勤状态']
                         const filterVal = ['id','time','usercode', 'username','stime','etime','outdesc','kqzt']
                         const list = response.data.data.cx
                         const data = this.formatJson(filterVal, list)
                         export_json_to_excel(tHeader, data, '考勤表')
                     }).catch(err => {
                         console.log("asdfasdfasdf");
                   });
                    this.formSubmiting = false;
                    this.formVisible = false;
                    this.$notify({
                        title: '成功',
                        message: '导出成功',
                        type: 'success',
                        duration: 1500,
                    });
                    console.log(response.data.data.cx)
                }).catch(error => {
                    console.log(error);
                });

            },
            formatJson(filterVal, jsonData) {
                return jsonData.map(v => filterVal.map(j => v[j]))
            },
            cqkh(tableT){
                var a = tableT
                a.forEach((item) => {
                    this.clearDialog();
                    if(item.stime==null||item.etime==null){
                        item.kqzt='矿工';
                        this.$api.system.wcheck.save({
                            data: item
                        }).then(response => {
                            //console.log('ok');
                        }).catch(error => {
                            console.log(error);
                        });
                    }
                    else if(item.stime.substring(0,10)==item.etime.substring(0,10)){
                        if(item.stime.substring(11,13)<9||(item.stime.substring(11,13)==9&&item.stime.substring(14,16)==0)){
                            if(item.etime.substring(11,13)>=17&&item.etime.substring(14,16)>=30){
                                item.kqzt='正常出勤';

                                this.$api.system.wcheck.save({
                                    data: item
                                }).then(response => {
                                    //console.log('ok');
                                }).catch(error => {
                                    console.log(error);
                                });
                            }else{
                                item.kqzt ='迟到早退';

                                this.$api.system.wcheck.save({
                                    data: item
                                }).then(response => {
                                    //console.log('ok');
                                }).catch(error => {
                                    console.log(error);
                                });
                            }
                        }else{
                            item.kqzt='迟到早退';

                            this.$api.system.wcheck.save({
                                data: item
                            }).then(response => {
                                //console.log('ok');
                            }).catch(error => {
                                console.log(error);
                            });
                        }
                    }
                    else if(item.stime>item.etime){
                        this.$notify({
                            title: '',
                            message: '编号'+item.id+'数据上下班时间,请检查后再考核!',
                            type: 'failed',
                            duration: 1500,
                        });
                    }
                });
                this.$notify({
                    title: '',
                    message: '考勤状态刷新成功!',
                    type: 'success',
                    duration: 1500,
                });
                this.getList();
            },
            expt(){
                const data = [{A:'1111',B:'2222'},{A:'1111',B:'2222'},{A:'1111',B:'2222'}]
                const header = {header: ['A','B'] }
                const xlsxName='1111'
                const outputXlsxFile = (data,header,xlsxName) => {

                    const ws = XLSX.utils.json_to_sheet(data, header)

                    const wb = XLSX.utils.book_new()

                    XLSX.utils.book_append_sheet(wb, ws, xlsxName)

                    XLSX.writeFile(wb, xlsxName + ".xlsx")

                }

                Vue.prototype.$outputXlsxFile
                    = outputXlsxFile

                Vue.prototype.$getExcelFileData = getExcelFileData
            }
        }
    };
</script>
